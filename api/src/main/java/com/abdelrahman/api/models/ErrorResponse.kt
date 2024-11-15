package com.abdelrahman.api.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("status")
    val errorDescription: String? = null
)