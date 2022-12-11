package com.borshevskiy.fkn_labs.domain

import androidx.lifecycle.LiveData
import com.borshevskiy.fkn_labs.utils.NetworkResult

interface MarvelHeroRepository {

    suspend fun getMarvelHeroesList(): NetworkResult<List<MarvelHero>>

    fun readMarvelHeroesList(): LiveData<List<MarvelHero>>

}