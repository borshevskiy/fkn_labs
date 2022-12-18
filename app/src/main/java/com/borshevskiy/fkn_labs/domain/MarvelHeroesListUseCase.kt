package com.borshevskiy.fkn_labs.domain

import javax.inject.Inject

class MarvelHeroesListUseCase @Inject constructor(private val repository: MarvelHeroRepository) {

    suspend operator fun invoke() = repository.getMarvelHeroesList()
}