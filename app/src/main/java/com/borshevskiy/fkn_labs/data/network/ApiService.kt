package com.borshevskiy.fkn_labs.data.network

import com.borshevskiy.fkn_labs.data.network.model.MarvelResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getAllHeroes(
        @Query("offset")offset:String = "${(0..1000).shuffled().last()}"
    ): Response<MarvelResponseDto>

    @GET("/v1/public/characters/")
    suspend fun getHeroDetailInfo(
        @Query("characterId")characterId:Int
    ): Response<MarvelResponseDto>
}