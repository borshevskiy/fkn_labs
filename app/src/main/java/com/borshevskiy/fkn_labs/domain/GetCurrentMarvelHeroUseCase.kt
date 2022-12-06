package com.borshevskiy.fkn_labs.domain

import javax.inject.Inject

class GetCurrentMarvelHeroUseCase @Inject constructor(private val repository: MarvelHeroRepository) {

    suspend operator fun invoke(id: Int) = repository.getCurrentMarvelHero(id)
}