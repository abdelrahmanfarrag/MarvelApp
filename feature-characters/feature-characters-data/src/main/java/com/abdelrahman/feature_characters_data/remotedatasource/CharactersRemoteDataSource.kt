package com.abdelrahman.feature_characters_data.remotedatasource

import com.abdelrahman.api.models.BaseMarvelAPIResponse
import com.abdelrahman.api.models.ResponseState
import com.abdelrahman.api.networkcheck.ICheckNetworkAvailability
import com.abdelrahman.api.validateresponse.ValidateResponse
import com.abdelrahman.feature_characters_data.api.CharactersApiInterface
import com.abdelrahman.feature_characters_data.models.CharactersResponse
import com.abdelrahman.feature_characters_data.models.GetCharactersRequest
import com.abdelrahman.feature_characters_data.utils.Constants
import com.abdelrahman.shared_domain.models.ErrorModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CharactersRemoteDataSource @Inject constructor(
    private val iValidateResponse: ValidateResponse,
    private val charactersApiInterface: CharactersApiInterface,
    private val iCheckNetworkAvailability: ICheckNetworkAvailability,
) : ICharactersRemoteDataSource {

    override suspend fun getCharacters(getCharactersRequest: GetCharactersRequest): ResponseState<BaseMarvelAPIResponse<CharactersResponse>> {
        return if (iCheckNetworkAvailability.isNetworkAvailable()) {
            iValidateResponse.validateApiResponse(
                charactersApiInterface.getCharacters(
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
            )
        } else
            ResponseState.StateError(ErrorModel.NoInternetConnectModel())
                    as ResponseState<BaseMarvelAPIResponse<CharactersResponse>>
    }
}