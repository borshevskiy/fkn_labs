package com.borshevskiy.fkn_labs.domain

interface MarvelHeroRepository {

    suspend fun getMarvelHeroesList(): List<MarvelHero>

    suspend fun getCurrentMarvelHero(id: Int): MarvelHero
}