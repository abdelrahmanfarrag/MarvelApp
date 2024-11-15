package com.abdelrahman.api.models

import com.google.gson.annotations.SerializedName

data class BaseMarvelAPIResponse<T>(
    @SerializedName("code")
    val responseCode: Int? = null,
    @SerializedName("copyright")
    val copyright: String? = null,
    @SerializedName("attributionText")
    val attributionText: String? = null,
    @SerializedName("attributionHTML")
    val attributionHTML: String? = null,
    @SerializedName("etag")
    val etag: String? = null,
    @SerializedName("data")
    val data: T? = null
)
