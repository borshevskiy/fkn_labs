package com.borshevskiy.fkn_labs.domain

import javax.inject.Inject

class ReadMarvelHeroesListUseCase @Inject constructor(private val repository: MarvelHeroRepository) {

    operator fun invoke() = repository.readMarvelHeroesList()
}