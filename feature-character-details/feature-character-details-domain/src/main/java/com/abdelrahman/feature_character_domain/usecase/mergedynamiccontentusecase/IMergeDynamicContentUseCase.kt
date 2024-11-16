package com.abdelrahman.feature_character_domain.usecase.mergedynamiccontentusecase

import com.abdelrahman.feature_character_domain.models.CharacterDetailsSection
import com.abdelrahman.feature_character_domain.models.ContentType
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow

interface IMergeDynamicContentUseCase {
    suspend operator fun invoke(contentMap: Map<ContentType, String?>): Flow<DataState<ArrayList<CharacterDetailsSection?>>>
}