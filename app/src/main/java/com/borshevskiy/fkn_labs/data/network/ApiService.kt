package com.borshevskiy.fkn_labs.data.network

import com.borshevskiy.fkn_labs.data.network.model.MarvelResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getAllHeroes(
        @Query("apikey")apikey:String = API_KEY,
        @Query("ts")ts:String = timeStamp,
        @Query("hash")hash:String = hash(),
        @Query("offset")offset:String = "${(0..1000).shuffled().last()}"
    ): Response<MarvelResponseDto>

    @GET("/v1/public/characters/")
    suspend fun getHeroDetailInfo(
        @Query("apikey")apikey:String = API_KEY,
        @Query("ts")ts:String = timeStamp,
        @Query("hash")hash:String = hash(),
        @Query("characterId")characterId:Int
    ): Response<MarvelResponseDto>

    companion object {
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "30bf94833fc9a2b0eb1f2dfe3f74ba4a"
        private const val PRIVATE_KEY = "e638599ff3e742aa4be041505e7c583619f4e115"
        fun hash():String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}