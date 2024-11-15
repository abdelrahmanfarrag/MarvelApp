package com.abdelrahman.feature_characters_data.api

import com.abdelrahman.api.models.BaseMarvelAPIResponse
import com.abdelrahman.feature_characters_data.models.CharactersResponse
import com.abdelrahman.feature_characters_data.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.QueryName

interface CharactersApiInterface {

    @GET(Constants.EndPoints.CHARACTERS)
    @JvmSuppressWildcards
    suspend fun getCharacters(
        @QueryMap extraDta : Map<String,Any?>
    ): Response<BaseMarvelAPIResponse<CharactersResponse>>

}