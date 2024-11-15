package com.abdelrahman.feature_characters_domain.repository

import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {

    suspend fun getCharacters(
        page: Int,
        searchKey: String?,
        pageSize: Int
    ): Flow<DataState<CharactersModel>>
}