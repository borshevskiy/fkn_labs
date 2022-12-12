package com.borshevskiy.fkn_labs.domain

import com.borshevskiy.fkn_labs.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MarvelHeroRepository {

    suspend fun getMarvelHeroesList(): NetworkResult<List<MarvelHero>>

    fun readMarvelHeroesList(): Flow<List<MarvelHero>>

}