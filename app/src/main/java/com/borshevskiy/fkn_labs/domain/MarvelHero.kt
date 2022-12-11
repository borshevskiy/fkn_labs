package com.borshevskiy.fkn_labs.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelHero(
    val id: Int,
    val name: String,
    val description: String,
    val imageLink: String): Parcelable