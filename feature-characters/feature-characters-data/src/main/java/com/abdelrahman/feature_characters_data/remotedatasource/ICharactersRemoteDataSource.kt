package com.abdelrahman.feature_characters_data.remotedatasource

import com.abdelrahman.api.models.BaseMarvelAPIResponse
import com.abdelrahman.api.models.ResponseState
import com.abdelrahman.feature_characters_data.models.CharactersResponse
import com.abdelrahman.feature_characters_data.models.GetCharactersRequest

interface ICharactersRemoteDataSource {

    suspend fun getCharacters(getCharactersRequest: GetCharactersRequest): ResponseState<BaseMarvelAPIResponse<CharactersResponse>>
}