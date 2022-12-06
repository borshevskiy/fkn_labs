package com.borshevskiy.fkn_labs.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borshevskiy.fkn_labs.domain.GetCurrentMarvelHeroUseCase
import com.borshevskiy.fkn_labs.domain.GetMarvelHeroesListUseCase
import com.borshevskiy.fkn_labs.domain.MarvelHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMarvelHeroesListUseCase: GetMarvelHeroesListUseCase,
    private val getCurrentMarvelHeroUseCase: GetCurrentMarvelHeroUseCase
): ViewModel() {


    private val _marvelHeroes = MutableLiveData<List<MarvelHero>>()
    val marvelHeroes: LiveData<List<MarvelHero>>
        get() = _marvelHeroes

    private fun getAllHeroes() {
        viewModelScope.launch {
            _marvelHeroes.value = getMarvelHeroesListUseCase()
        }
    }

    init {
        getAllHeroes()
    }

}