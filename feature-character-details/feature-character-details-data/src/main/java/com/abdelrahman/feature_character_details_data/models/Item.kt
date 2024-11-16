package com.abdelrahman.feature_character_details_data.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null
)