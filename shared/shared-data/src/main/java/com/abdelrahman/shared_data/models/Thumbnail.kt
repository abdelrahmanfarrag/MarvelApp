package com.abdelrahman.shared_data.models

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null
)

fun Thumbnail.createValidThumbnailURL(imageSpecs: ImageSpecs): String? {
    return path?.plus("/${imageSpecs.spec}.")?.plus(extension)?.replace("http","https")
}