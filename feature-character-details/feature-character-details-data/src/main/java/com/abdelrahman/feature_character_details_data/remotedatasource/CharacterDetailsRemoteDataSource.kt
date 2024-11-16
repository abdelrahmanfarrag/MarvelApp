package com.abdelrahman.feature_character_details_data.remotedatasource

import com.abdelrahman.api.models.BaseMarvelAPIResponse
import com.abdelrahman.api.models.ResponseState
import com.abdelrahman.api.networkcheck.ICheckNetworkAvailability
import com.abdelrahman.api.validateresponse.IValidateResponse
import com.abdelrahman.feature_character_details_data.apiinterface.CharacterDetailsApiInterface
import com.abdelrahman.feature_character_details_data.models.DynamicContentResponse
import com.abdelrahman.shared_domain.models.ErrorModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CharacterDetailsRemoteDataSource @Inject constructor(
    private val characterDetailsApiInterface: CharacterDetailsApiInterface,
    private val iCheckNetworkAvailability: ICheckNetworkAvailability,
    private val iValidateResponse: IValidateResponse
) : ICharacterDetailsRemoteDataSource {
    override suspend fun loadDynamicDetailsContent(url: String?): ResponseState<BaseMarvelAPIResponse<DynamicContentResponse>> {
        return if (iCheckNetworkAvailability.isNetworkAvailable()) {
            iValidateResponse.validateApiResponse(
                characterDetailsApiInterface.loadDynamicDetailsContent(
                    url
                )
            )
        } else
            ResponseState.StateError(errorModel = ErrorModel.NoInternetConnectModel) as ResponseState<BaseMarvelAPIResponse<DynamicContentResponse>>
    }
}