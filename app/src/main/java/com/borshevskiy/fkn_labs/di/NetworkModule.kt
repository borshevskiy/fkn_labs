package com.borshevskiy.fkn_labs.di

import com.borshevskiy.fkn_labs.BuildConfig
import com.borshevskiy.fkn_labs.data.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val timestamp = Timestamp(System.currentTimeMillis()).time.toString()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    fun hash() = BigInteger(1, MessageDigest.getInstance("MD5").digest(
        "$timestamp${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}".toByteArray()))
        .toString(16).padStart(32, '0')


    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = Interceptor {
        val request = it.request()
        val newHttpUrl = request.url.newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("hash", hash()).toString()
        val actualRequest = request.newBuilder().url(newHttpUrl).build()
        it.proceed(actualRequest)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        moshiConverterFactory: MoshiConverterFactory,
        client: OkHttpClient
    ): ApiService =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(moshiConverterFactory)
            .client(client)
            .build().create(ApiService::class.java)

}