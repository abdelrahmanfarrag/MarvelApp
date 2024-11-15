package com.abdelrahman.feature_characters_data.models

data class GetCharactersRequest(
    val page: Int,
    val searchKey: String? = null,
    val pageSize: Int = 20
)