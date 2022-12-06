package com.borshevskiy.fkn_labs.data.network

import com.borshevskiy.fkn_labs.data.network.model.MarvelResponseDto
import com.borshevskiy.fkn_labs.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.random.Random

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getAllHeroes(
        @Query("apikey")apikey:String = Constants.API_KEY,
        @Query("ts")ts:String = Constants.timeStamp,
        @Query("hash")hash:String = Constants.hash(),
        @Query("offset")offset:String = Random.nextInt(0,1000).toString()
    ): Response<MarvelResponseDto>

    @GET("/v1/public/characters/")
    suspend fun getHeroDetailInfo(
        @Query("apikey")apikey:String = Constants.API_KEY,
        @Query("ts")ts:String = Constants.timeStamp,
        @Query("hash")hash:String = Constants.hash(),
        @Query("characterId")characterId:Int
    ): Response<MarvelResponseDto>
}