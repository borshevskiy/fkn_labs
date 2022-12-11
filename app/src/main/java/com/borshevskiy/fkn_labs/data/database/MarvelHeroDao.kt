package com.borshevskiy.fkn_labs.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarvelHeroDao {

    @Query("SELECT * FROM all_heroes_list ORDER BY id DESC")
    fun getHeroesList(): LiveData<List<MarvelHeroDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarvelHeroList(marvelHeroList: List<MarvelHeroDbModel>)

    @Query("DELETE FROM all_heroes_list")
    suspend fun deleteAll()
}