package com.borshevskiy.fkn_labs.domain

import javax.inject.Inject

class GetMarvelHeroesListUseCase @Inject constructor(private val repository: MarvelHeroRepository) {

    suspend operator fun invoke() = repository.getMarvelHeroesList()
}