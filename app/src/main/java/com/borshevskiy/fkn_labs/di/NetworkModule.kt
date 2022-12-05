package com.borshevskiy.fkn_labs.di

import com.borshevskiy.fkn_labs.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    fun provideMoshi(): Moshi = Moshi.Builder().build()
//
//    @Singleton
//    @Provides
//    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun baseUrl() = "https://gateway.marvel.com/"

    @Singleton
    @Provides
    fun provideRetrofitInstance(baseUrl: String): ApiService =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

}