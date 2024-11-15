package com.abdelrahman.feature_characters_data.models


import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("type")
    val type: String?=null,
    @SerializedName("url")
    val url: String?=null
)