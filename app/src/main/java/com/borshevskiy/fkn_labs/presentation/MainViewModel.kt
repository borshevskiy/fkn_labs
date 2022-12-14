package com.borshevskiy.fkn_labs.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.borshevskiy.fkn_labs.domain.GetMarvelHeroesListUseCase
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
    private val getMarvelHeroesListUseCase: GetMarvelHeroesListUseCase,
    private val readMarvelHeroesListUseCase: ReadMarvelHeroesListUseCase,
    application: Application,
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(MainState(isLoading = true))
    val state: StateFlow<MainState> = _state

    fun obtainEvent(event: MainEvent) {
        when (event) {
            is LoadFromApiEvent -> getAllHeroesFromApiSafeCall()
            is GetCacheFromDBEvent -> getCache()
        }
    }


    private fun getAllHeroesFromApiSafeCall() {
        viewModelScope.launch {
            if (hasInternetConnection()) {
                _state.value = when (val response = getMarvelHeroesListUseCase()) {
                    is NetworkResult.Success -> MainState(response.data, false, null)
                    is NetworkResult.Error -> MainState(null, false, response.message)
                }
            } else _state.value = MainState(null, false, "No Internet Connection.")
        }
    }

    private fun getCache() {
        viewModelScope.launch(Dispatchers.IO) {
            val cache = readMarvelHeroesListUseCase()
            if (cache.isNotEmpty()) _state.value = MainState(cache, false, null) else _state.value =
                MainState(null, false, "No Internet Connection.\nNo Cache.")
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