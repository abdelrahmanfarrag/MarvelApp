package com.abdelrahman.feature_characters_data.remotedatasource

import com.abdelrahman.api.models.BaseMarvelAPIResponse
import com.abdelrahman.api.models.ResponseState
import com.abdelrahman.api.validateresponse.ValidateResponse
import com.abdelrahman.feature_characters_data.api.CharactersApiInterface
import com.abdelrahman.feature_characters_data.models.CharactersResponse
import com.abdelrahman.feature_characters_data.models.GetCharactersRequest
import com.abdelrahman.feature_characters_data.utils.Constants
import javax.inject.Inject

class CharactersRemoteDataSource @Inject constructor(
    private val iValidateResponse: ValidateResponse,
    private val charactersApiInterface: CharactersApiInterface
) : ICharactersRemoteDataSource {

    override suspend fun getCharacters(getCharactersRequest: GetCharactersRequest): ResponseState<BaseMarvelAPIResponse<CharactersResponse>> {
        val getCharactersResponse = charactersApiInterface.getCharacters(
            if (!getCharactersRequest.searchKey.isNullOrEmpty() || !getCharactersRequest.searchKey.isNullOrBlank())
                mapOf(
                    Constants.Queries.OFFSET to getCharactersRequest.page,
                    Constants.Queries.SEARCH_KEY to getCharactersRequest.searchKey,
                    Constants.Queries.LIMIT to getCharactersRequest.pageSize
                ) else
                mapOf(
                    Constants.Queries.OFFSET to getCharactersRequest.page,
                    Constants.Queries.LIMIT to getCharactersRequest.pageSize
                )
        )
        return iValidateResponse.validateApiResponse(getCharactersResponse)
    }
}