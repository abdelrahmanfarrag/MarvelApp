package com.abdelrahman.feature_character_details_data.remotedatasource

import com.abdelrahman.api.models.BaseMarvelAPIResponse
import com.abdelrahman.api.models.ResponseState
import com.abdelrahman.feature_character_details_data.models.DynamicContentResponse

interface ICharacterDetailsRemoteDataSource {
    suspend fun loadDynamicDetailsContent(url: String?): ResponseState<BaseMarvelAPIResponse<DynamicContentResponse>>
}