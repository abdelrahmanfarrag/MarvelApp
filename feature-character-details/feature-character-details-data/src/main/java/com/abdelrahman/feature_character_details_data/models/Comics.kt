package com.abdelrahman.feature_character_details_data.models


import com.google.gson.annotations.SerializedName

data class Comics(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<Item?>? = null,
    @SerializedName("returned")
    val returned: Int? = null
)