package com.abdelrahman.feature_characters_domain.usecase.getcharacters

import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow

interface IGetCharactersUseCase {

    suspend operator fun invoke(
        page: Int,
        searchKey: String?,
        pageSize:Int
    ): Flow<DataState<CharactersModel>>
}