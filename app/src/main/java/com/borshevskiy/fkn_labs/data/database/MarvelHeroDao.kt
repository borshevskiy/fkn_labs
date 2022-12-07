package com.borshevskiy.fkn_labs.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarvelHeroDao {

    @Query("SELECT * FROM all_heroes_list ORDER BY id DESC")
    fun getHeroesList(): List<MarvelHeroDbModel>

    @Query("SELECT * FROM all_heroes_list WHERE id == :id")
    fun getInfoAboutMarvelHero(id: Int): MarvelHeroDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarvelHeroList(marvelHeroList: List<MarvelHeroDbModel>)
}