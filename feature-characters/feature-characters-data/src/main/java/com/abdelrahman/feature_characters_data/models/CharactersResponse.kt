package com.abdelrahman.feature_characters_data.models


import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("count")
    val count: Int?=null,
    @SerializedName("limit")
    val limit: Int?=null,
    @SerializedName("offset")
    val offset: Int?=null,
    @SerializedName("results")
    val results: List<Result>?=null,
    @SerializedName("total")
    val total: Int?=null
)