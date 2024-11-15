package com.abdelrahman.feature_characters_data.models


import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null
)

fun Thumbnail.createValidThumbnailURL(): String? {
    return path?.plus("/landscape_incredible.")?.plus(extension)
}