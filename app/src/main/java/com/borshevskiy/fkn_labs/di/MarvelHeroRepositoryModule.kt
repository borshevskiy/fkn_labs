package com.borshevskiy.fkn_labs.di

import com.borshevskiy.fkn_labs.data.repository.MarvelHeroRepositoryImpl
import com.borshevskiy.fkn_labs.domain.MarvelHeroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MarvelHeroRepositoryModule {

    @Binds
    abstract fun bindRepository(impl: MarvelHeroRepositoryImpl): MarvelHeroRepository
}