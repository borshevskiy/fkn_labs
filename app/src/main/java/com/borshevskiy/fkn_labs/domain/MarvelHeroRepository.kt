package com.borshevskiy.fkn_labs.domain

import com.borshevskiy.fkn_labs.domain.utils.NetworkResult

interface MarvelHeroRepository {

    suspend fun getMarvelHeroesList(): NetworkResult<List<MarvelHero>>

    suspend fun getMarvelHeroInfo(heroId: Int): NetworkResult<MarvelHero>

    fun readMarvelHeroesList(): List<MarvelHero>

}