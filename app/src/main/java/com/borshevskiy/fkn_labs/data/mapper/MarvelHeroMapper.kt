package com.borshevskiy.fkn_labs.data.mapper

import com.borshevskiy.fkn_labs.data.database.MarvelHeroDbModel
import com.borshevskiy.fkn_labs.data.network.model.MarvelResponseDto
import com.borshevskiy.fkn_labs.domain.MarvelHero
import javax.inject.Inject

class MarvelHeroMapper @Inject constructor() {

    private fun mapResultToMarvelHero(result: com.borshevskiy.fkn_labs.data.network.model.Result)
    = with(result) { MarvelHero(id, name, description, "${thumbnail.path}.${thumbnail.extension}") }

    fun mapDtoToMarvelHero(dto: MarvelResponseDto) = dto.data.results.map { mapResultToMarvelHero(it) }

    private fun mapResultToMarvelHeroDbModel(result: com.borshevskiy.fkn_labs.data.network.model.Result)
            = with(result) { MarvelHeroDbModel(id, name, description, "${thumbnail.path}.${thumbnail.extension}") }

    fun mapDtoToMarvelHeroDbModel(dto: MarvelResponseDto) = dto.data.results.map { mapResultToMarvelHeroDbModel(it) }

    private fun mapMarvelHeroDbModelToMarvelHero(marvelHeroDbModel: MarvelHeroDbModel)
            = with(marvelHeroDbModel) { MarvelHero(id, name, description, imageLink) }

    fun mapListDbModelToMarvelHero(listDbModel: List<MarvelHeroDbModel>) = listDbModel.map { mapMarvelHeroDbModelToMarvelHero(it) }


}