package com.borshevskiy.fkn_labs.domain

import com.borshevskiy.fkn_labs.domain.utils.NetworkResult

interface MarvelHeroRepository {

    suspend fun getMarvelHeroesList(): NetworkResult<List<MarvelHero>>

    fun readMarvelHeroesList(): List<MarvelHero>

}