package com.abdelrahman.feature_character_details_data.models


import com.google.gson.annotations.SerializedName

data class DynamicContentResponse(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("offset")
    val offset: Int? = 0,
    @SerializedName("results")
    val results: List<Result>? = listOf(),
    @SerializedName("total")
    val total: Int? = 0
)