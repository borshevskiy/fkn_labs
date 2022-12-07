package com.borshevskiy.fkn_labs.domain

import javax.inject.Inject

class ReadMarvelHeroInfoUseCase @Inject constructor(private val repository: MarvelHeroRepository) {

    operator fun invoke(id: Int) = repository.readMarvelHeroInfo(id)
}