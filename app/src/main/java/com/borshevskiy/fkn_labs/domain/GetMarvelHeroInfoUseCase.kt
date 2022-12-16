package com.borshevskiy.fkn_labs.domain

import javax.inject.Inject

class GetMarvelHeroInfoUseCase @Inject constructor(private val repository: MarvelHeroRepository) {

    suspend operator fun invoke(heroId: Int) = repository.getMarvelHeroInfo(heroId)
}