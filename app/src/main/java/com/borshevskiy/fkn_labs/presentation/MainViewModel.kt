package com.borshevskiy.fkn_labs.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.borshevskiy.fkn_labs.domain.GetMarvelHeroesListUseCase
import com.borshevskiy.fkn_labs.domain.MarvelHero
import com.borshevskiy.fkn_labs.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMarvelHeroesListUseCase: GetMarvelHeroesListUseCase,
    application: Application
    ): AndroidViewModel(application) {

    private val _marvelHeroes = MutableLiveData<NetworkResult<List<MarvelHero>>>()
    val marvelHeroes: LiveData<NetworkResult<List<MarvelHero>>>
        get() = _marvelHeroes

    private fun getAllHeroesSafeCall() {
        _marvelHeroes.value = NetworkResult.Loading()
        viewModelScope.launch {
            if (hasInternetConnection()) {
                try {
                    _marvelHeroes.value = getMarvelHeroesListUseCase()
                } catch (e: Exception) {
                    _marvelHeroes.value = NetworkResult.Error("Characters not found.")
                }
            } else {
                _marvelHeroes.value = NetworkResult.Error("No Internet Connection.")
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    init {
        getAllHeroesSafeCall()
    }
}