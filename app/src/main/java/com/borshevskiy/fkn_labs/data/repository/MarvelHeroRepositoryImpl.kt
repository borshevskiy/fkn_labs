package com.borshevskiy.fkn_labs.data.repository

import com.borshevskiy.fkn_labs.data.database.MarvelHeroDao
import com.borshevskiy.fkn_labs.data.mapper.MarvelHeroMapper
import com.borshevskiy.fkn_labs.data.network.ApiService
import com.borshevskiy.fkn_labs.domain.MarvelHero
import com.borshevskiy.fkn_labs.domain.MarvelHeroRepository
import com.borshevskiy.fkn_labs.domain.utils.NetworkResult
import javax.inject.Inject

class MarvelHeroRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val marvelHeroDao: MarvelHeroDao,
    private val mapper: MarvelHeroMapper
) : MarvelHeroRepository {

    override suspend fun getMarvelHeroesList(): NetworkResult<List<MarvelHero>> {
        apiService.getAllHeroes().let {
            return when {
                it.isSuccessful -> {
                    /** Очищаю БД перед загрузкой новых героев, т.к. из-за рандомизатара подгружаются
                     * каждый раз новые персонажи **/
                    marvelHeroDao.deleteAll()
                    marvelHeroDao.insertMarvelHeroList(mapper.mapDtoToMarvelHeroDbModel(it.body()!!))
                    NetworkResult.Success(mapper.mapDtoToMarvelHero(it.body()!!))
                }
                else -> NetworkResult.Error(it.message())
            }
        }
    }

    override suspend fun getMarvelHeroInfo(heroId: Int): NetworkResult<MarvelHero> {
        apiService.getHeroDetailInfo(heroId).let {
            return when {
                it.isSuccessful -> NetworkResult.Success(mapper.mapDtoToMarvelHero(it.body()!!).first())
                else -> NetworkResult.Error(it.message())
            }
        }
    }

    override fun readMarvelHeroesList(): List<MarvelHero> = mapper.mapListDbModelToMarvelHero(marvelHeroDao.getHeroesList())
}