package com.borshevskiy.fkn_labs.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MarvelHeroDbModel::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun marvelHeroDao(): MarvelHeroDao

}