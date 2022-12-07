package com.borshevskiy.fkn_labs.domain

import com.borshevskiy.fkn_labs.utils.NetworkResult

interface MarvelHeroRepository {

    suspend fun getMarvelHeroesList(): NetworkResult<List<MarvelHero>>

    fun readMarvelHeroesList(): List<MarvelHero>

    fun readMarvelHeroInfo(id: Int): MarvelHero

}