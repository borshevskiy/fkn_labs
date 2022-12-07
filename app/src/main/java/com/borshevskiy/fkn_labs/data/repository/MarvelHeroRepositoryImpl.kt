package com.borshevskiy.fkn_labs.data.repository

import com.borshevskiy.fkn_labs.data.database.MarvelHeroDao
import com.borshevskiy.fkn_labs.data.mapper.MarvelHeroMapper
import com.borshevskiy.fkn_labs.data.network.ApiService
import com.borshevskiy.fkn_labs.data.network.model.MarvelResponseDto
import com.borshevskiy.fkn_labs.domain.MarvelHero
import com.borshevskiy.fkn_labs.domain.MarvelHeroRepository
import com.borshevskiy.fkn_labs.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class MarvelHeroRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val marvelHeroDao: MarvelHeroDao,
    private val mapper: MarvelHeroMapper
) : MarvelHeroRepository {

    override suspend fun getMarvelHeroesList(): NetworkResult<List<MarvelHero>> {
        return handleResponse(apiService.getAllHeroes())
    }

    override fun readMarvelHeroesList(): List<MarvelHero> {
        return mapper.mapListDbModelToMarvelHero(marvelHeroDao.getHeroesList())
    }

    override fun readMarvelHeroInfo(id: Int): MarvelHero {
        return mapper.mapMarvelHeroDbModelToMarvelHero(marvelHeroDao.getInfoAboutMarvelHero(id))
    }

    private suspend fun handleResponse(response: Response<MarvelResponseDto>): NetworkResult<List<MarvelHero>> {
        return when {
            response.message().toString().contains("timeout") -> NetworkResult.Error("Timeout")
            response.code() == 409 -> NetworkResult.Error("Invalid Response.")
            response.body()!!.data.results.isEmpty() -> NetworkResult.Error("Characters not found")
            response.isSuccessful -> {
                marvelHeroDao.insertMarvelHeroList(mapper.mapDtoToMarvelHeroDbModel(response.body()!!))
                NetworkResult.Success(mapper.mapDtoToMarvelHero(response.body()!!))
            }
            else -> NetworkResult.Error(response.message())
        }
    }
}