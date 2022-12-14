package com.borshevskiy.fkn_labs.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stories(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<HeroItem>,
    @Json(name = "returned")
    val returned: Int
)