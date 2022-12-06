package com.borshevskiy.fkn_labs.data.repository

import com.borshevskiy.fkn_labs.data.mapper.MarvelHeroMapper
import com.borshevskiy.fkn_labs.data.network.ApiService
import com.borshevskiy.fkn_labs.domain.MarvelHero
import com.borshevskiy.fkn_labs.domain.MarvelHeroRepository
import javax.inject.Inject


class MarvelHeroRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: MarvelHeroMapper
) : MarvelHeroRepository {
    override suspend fun getMarvelHeroesList(): List<MarvelHero> {
        val response = apiService.getAllHeroes()
        return if (response.isSuccessful) { mapper.mapDtoToMarvelHero(response.body()!!) } else { emptyList() }
    }

    override suspend fun getCurrentMarvelHero(id: Int): MarvelHero {
        val response = apiService.getHeroDetailInfo(characterId = id)
        return if (response.isSuccessful) { mapper.mapResultToMarvelHero(response.body()!!.data.results[0]) } else { MarvelHero(0, "", "", "") }
    }

}