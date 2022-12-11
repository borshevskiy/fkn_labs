package com.borshevskiy.fkn_labs.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.borshevskiy.fkn_labs.data.database.MarvelHeroDao
import com.borshevskiy.fkn_labs.data.mapper.MarvelHeroMapper
import com.borshevskiy.fkn_labs.data.network.ApiService
import com.borshevskiy.fkn_labs.domain.MarvelHero
import com.borshevskiy.fkn_labs.domain.MarvelHeroRepository
import com.borshevskiy.fkn_labs.utils.NetworkResult
import javax.inject.Inject

class MarvelHeroRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val marvelHeroDao: MarvelHeroDao,
    private val mapper: MarvelHeroMapper
) : MarvelHeroRepository {

    override suspend fun getMarvelHeroesList(): NetworkResult<List<MarvelHero>> {
        val response = apiService.getAllHeroes()
        return when {
            response.message().toString().contains("timeout") -> NetworkResult.Error("Timeout")
            response.code() == 409 -> NetworkResult.Error("Invalid Response.")
            response.body()!!.data.results.isEmpty() -> NetworkResult.Error("Characters not found")
            response.isSuccessful -> {
                /** Очищаю БД перед загрузкой новых героев, т.к. из-за рандомизатара подгружаются
                 * каждый раз новые персонажи **/
                marvelHeroDao.deleteAll()
                marvelHeroDao.insertMarvelHeroList(mapper.mapDtoToMarvelHeroDbModel(response.body()!!))
                NetworkResult.Success(mapper.mapDtoToMarvelHero(response.body()!!))
            }
            else -> NetworkResult.Error(response.message())
        }
    }

    override fun readMarvelHeroesList(): LiveData<List<MarvelHero>> {
        return Transformations.map(marvelHeroDao.getHeroesList()) {
            mapper.mapListDbModelToMarvelHero(it)
        }
    }
}