package com.borshevskiy.fkn_labs.presentation

import com.borshevskiy.fkn_labs.domain.MarvelHero

data class MainState(
    val marvelHeroList: List<MarvelHero>? = null,
    val heroInfo: MarvelHero? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
