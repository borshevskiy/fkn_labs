package com.borshevskiy.fkn_labs.data.network.model


import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemXXX>,
    @SerializedName("returned")
    val returned: Int
)