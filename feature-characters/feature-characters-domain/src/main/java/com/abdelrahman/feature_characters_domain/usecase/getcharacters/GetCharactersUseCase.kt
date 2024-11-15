package com.abdelrahman.feature_characters_domain.usecase.getcharacters

import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.feature_characters_domain.repository.ICharactersRepository
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val iCharactersRepository: ICharactersRepository) :
    IGetCharactersUseCase {
    override suspend fun invoke(
        page: Int,
        searchKey: String?,
        pageSize: Int
    ): Flow<DataState<CharactersModel>> {
        return iCharactersRepository.getCharacters(page, searchKey, pageSize)
    }
}