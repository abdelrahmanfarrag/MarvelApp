package com.abdelrahman.feature_characters_domain.usecase.mapcharacters

import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.shared_domain.models.DataState
import com.abdelrahman.shared_domain.models.ErrorModel
import javax.inject.Inject

class MapGetCharacters @Inject constructor() : IMapGetCharacters {
    override suspend fun invoke(dataState: DataState<CharactersModel>): DataState<CharactersModel> {
        return if (dataState is DataState.Success) {
            val charactersList = dataState.data?.characters
            return if (charactersList.isNullOrEmpty())
                DataState.Error(errorModel = ErrorModel.NoDataError)
            else
                dataState

        } else
            dataState
    }
}