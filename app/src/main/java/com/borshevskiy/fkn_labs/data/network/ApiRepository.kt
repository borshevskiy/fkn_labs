package com.borshevskiy.fkn_labs.data.network

import android.util.Log
import com.borshevskiy.fkn_labs.data.network.model.MarvelResponseDto
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllHeroes(): Response<MarvelResponseDto> {
        Log.d("TEST", apiService.getAllHeroes().message())
        return apiService.getAllHeroes()
    }

}