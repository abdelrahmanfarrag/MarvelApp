package com.abdelrahman.feature_character_domain.usecase

import com.abdelrahman.feature_character_domain.models.CharacterDetailsSection
import com.abdelrahman.feature_character_domain.models.ContentType
import com.abdelrahman.feature_character_domain.repository.ICharacterDetailsRepository
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadDynamicContentUseCase @Inject constructor(
    private val iCharacterDetailsRepository: ICharacterDetailsRepository
) : ILoadDynamicContentUseCase {

    override suspend fun invoke(
        contentType: ContentType,
        url: String?
    ): Flow<DataState<CharacterDetailsSection>> {
        return iCharacterDetailsRepository.loadDynamicDetailsContent(url, contentType)
    }
}