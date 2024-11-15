package com.abdelrahman.feature_characters_data.repository

import com.abdelrahman.api.mappers.mapToDataState
import com.abdelrahman.feature_characters_data.models.GetCharactersRequest
import com.abdelrahman.feature_characters_data.models.createValidThumbnailURL
import com.abdelrahman.feature_characters_data.remotedatasource.ICharactersRemoteDataSource
import com.abdelrahman.feature_characters_domain.models.Character
import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.feature_characters_domain.repository.ICharactersRepository
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val iCharactersRemoteDataSource: ICharactersRemoteDataSource) :
    ICharactersRepository {

    override suspend fun getCharacters(
        page: Int,
        searchKey: String?,
        pageSize: Int
    ): Flow<DataState<CharactersModel>> {
        return iCharactersRemoteDataSource.getCharacters(
            GetCharactersRequest(
                page,
                searchKey,
                pageSize
            )
        ).mapToDataState { charactersResponse ->
            CharactersModel(
                charactersResponse?.data?.total,
                characters = charactersResponse?.data?.results?.map { response ->
                    return@map Character(
                        id = response.id,
                        name = response.name,
                        description = response.description,
                        modifiedDate = response.modified,
                        imageURL = response.thumbnail?.createValidThumbnailURL()
                    )
                } as ArrayList<Character>)

        }
    }
}