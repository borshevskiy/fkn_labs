package com.borshevskiy.fkn_labs.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.borshevskiy.fkn_labs.domain.GetMarvelHeroesListUseCase
import com.borshevskiy.fkn_labs.domain.ReadMarvelHeroesListUseCase
import com.borshevskiy.fkn_labs.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMarvelHeroesListUseCase: GetMarvelHeroesListUseCase,
    private val readMarvelHeroesListUseCase: ReadMarvelHeroesListUseCase,
    application: Application
): AndroidViewModel(application) {

    var state by mutableStateOf(MarvelHeroState(isLoading = true))
        private set


    private fun getAllHeroesSafeCall() {
        viewModelScope.launch {
            if (hasInternetConnection()) {
                state = when (val response = getMarvelHeroesListUseCase()) {
                    is NetworkResult.Success -> state.copy(marvelHeroList = response.data)
                    is NetworkResult.Error -> state.copy(error = response.message)
                }
            } else {
                state = state.copy(isLoading = false, error = "No Internet Connection.")
                viewModelScope.launch(Dispatchers.IO) {
                    val cache = readMarvelHeroesListUseCase()
                    Log.d("TEST", "$cache")
                }
//                    state = if (cache.isNotEmpty()) state.copy(marvelHeroList = cache) else state.copy(isLoading = false, error = "No Internet Connection.")
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