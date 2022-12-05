package com.borshevskiy.fkn_labs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borshevskiy.fkn_labs.data.network.ApiRepository
import com.borshevskiy.fkn_labs.data.network.model.MarvelResponseDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    private val _marvelHeroes = MutableLiveData<MarvelResponseDto>()
    val marvelHeroes: LiveData<MarvelResponseDto>
        get() = _marvelHeroes

    private fun getAllHeroes() {
        viewModelScope.launch {
            repository.getAllHeroes().let {
                if (it.isSuccessful) {
                    _marvelHeroes.postValue(it.body())
                }
            }
        }
    }

    init {
        getAllHeroes()
    }

}