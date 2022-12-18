package com.borshevskiy.fkn_labs.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.borshevskiy.fkn_labs.domain.MarvelHeroInfoUseCase
import com.borshevskiy.fkn_labs.domain.MarvelHeroesListUseCase
import com.borshevskiy.fkn_labs.domain.ReadMarvelHeroesListUseCase
import com.borshevskiy.fkn_labs.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val marvelHeroesListUseCase: MarvelHeroesListUseCase,
    private val readMarvelHeroesListUseCase: ReadMarvelHeroesListUseCase,
    private val marvelHeroInfoUseCase: MarvelHeroInfoUseCase,
    application: Application,
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(MainState(isLoading = true))
    val state: StateFlow<MainState> = _state

    fun obtainEvent(event: MainEvent) {
        when (event) {
            is LoadHeroListFromApiEvent -> getAllHeroesFromApiSafeCall()
            is LoadHeroInfoFromApiEvent -> getHeroInfoFromApiSafeCall(event.heroId)
            is GetCacheFromDBEvent -> getCache()
        }
    }

    private fun getHeroInfoFromApiSafeCall(heroId: Int) {
        viewModelScope.launch {
            if (hasInternetConnection()) {
                _state.value = when (val response = marvelHeroInfoUseCase(heroId)) {
                    is NetworkResult.Success -> MainState(state.value.marvelHeroList, response.data, false, null)
                    is NetworkResult.Error -> MainState(state.value.marvelHeroList, null, false, response.message)
                }
            } else {
                if (!state.value.marvelHeroList.isNullOrEmpty()) {
                    val hero = state.value.marvelHeroList!!.firstOrNull { it.id == heroId }
                    _state.value = MainState(state.value.marvelHeroList, hero, false, null)
                } else _state.value = MainState(state.value.marvelHeroList, null, false, "No Internet Connection.")
            }
        }
    }


    private fun getAllHeroesFromApiSafeCall() {
        _state.value = MainState(null, null, false, null)
        viewModelScope.launch {
            if (hasInternetConnection()) {
                _state.value = when (val response = marvelHeroesListUseCase()) {
                    is NetworkResult.Success -> MainState(response.data, null, false, null)
                    is NetworkResult.Error -> MainState(null, null, false, response.message)
                }
            } else _state.value = MainState(null, null, false, "No Internet Connection.")
        }
    }

    private fun getCache() {
        viewModelScope.launch(Dispatchers.IO) {
            val cache = readMarvelHeroesListUseCase()
            if (cache.isNotEmpty()) _state.value = MainState(cache,null, false, null) else _state.value =
                MainState(null,null,false, "No Internet Connection.\nNo Cache.")
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
}