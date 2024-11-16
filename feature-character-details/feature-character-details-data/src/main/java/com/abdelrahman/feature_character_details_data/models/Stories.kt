package com.abdelrahman.feature_character_details_data.models


import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    val available: Int? = 0,
    @SerializedName("collectionURI")
    val collectionURI: String? = "",
    @SerializedName("items")
    val items: List<ItemXXX>? = listOf(),
    @SerializedName("returned")
    val returned: Int? = 0
)