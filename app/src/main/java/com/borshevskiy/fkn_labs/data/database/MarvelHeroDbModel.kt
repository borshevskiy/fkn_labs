package com.borshevskiy.fkn_labs.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_heroes_list")
data class MarvelHeroDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageLink: String
)


