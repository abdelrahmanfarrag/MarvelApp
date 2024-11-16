package com.abdelrahman.feature_character_domain.usecase

import com.abdelrahman.feature_character_domain.models.CharacterDetailsSection
import com.abdelrahman.feature_character_domain.models.ContentType
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow

interface ILoadDynamicContentUseCase {
    suspend operator fun invoke(
        contentType: ContentType,
        url: String?
    ) : Flow<DataState<CharacterDetailsSection>>
}