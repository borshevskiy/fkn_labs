package com.borshevskiy.fkn_labs.di

import com.borshevskiy.fkn_labs.data.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Provides
    fun baseUrl() = "https://gateway.marvel.com/"

    @Singleton
    @Provides
    fun provideRetrofitInstance(baseUrl: String, moshiConverterFactory: MoshiConverterFactory): ApiService =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(moshiConverterFactory)
            .build().create(ApiService::class.java)

}