package com.abdelrahman.shared_domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val landscapeImage: String?,
    val squareImage: String?,
    val portraitImage: String?
)
