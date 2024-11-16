package com.abdelrahman.feature_character_details_data.apiinterface

import com.abdelrahman.api.models.BaseMarvelAPIResponse
import com.abdelrahman.feature_character_details_data.models.DynamicContentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterDetailsApiInterface {

    @GET
    suspend fun loadDynamicDetailsContent(
        @Url url: String?
    ): Response<BaseMarvelAPIResponse<DynamicContentResponse>>
}