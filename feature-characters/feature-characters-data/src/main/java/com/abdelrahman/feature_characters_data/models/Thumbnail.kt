package com.abdelrahman.feature_characters_data.models


import com.abdelrahman.feature_characters_data.utils.ImageSpecs
import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null
)

fun Thumbnail.createValidThumbnailURL(imageSpecs: ImageSpecs): String? {
    return path?.plus("/${imageSpecs.spec}.")?.plus(extension)
}