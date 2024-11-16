package com.abdelrahman.feature_character_domain.repository

import com.abdelrahman.feature_character_domain.models.CharacterDetailsSection
import com.abdelrahman.feature_character_domain.models.ContentType
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow

interface ICharacterDetailsRepository {

    suspend fun loadDynamicDetailsContent(
        url: String?,
        contentType: ContentType
    ): Flow<DataState<CharacterDetailsSection>>
}