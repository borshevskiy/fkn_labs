package com.borshevskiy.fkn_labs.data.mapper

import com.borshevskiy.fkn_labs.data.network.model.MarvelResponseDto
import com.borshevskiy.fkn_labs.domain.MarvelHero
import javax.inject.Inject

class MarvelHeroMapper @Inject constructor() {

    fun mapResultToMarvelHero(result: com.borshevskiy.fkn_labs.data.network.model.Result)
    = with(result) { MarvelHero(id, name, description, "${thumbnail.path}.${thumbnail.extension}") }

    fun mapDtoToMarvelHero(dto: MarvelResponseDto) = dto.data.results.map { mapResultToMarvelHero(it) }
}