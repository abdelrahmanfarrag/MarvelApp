package com.abdelrahman.feature_character_details_data.repository

import android.util.Log
import com.abdelrahman.api.mappers.mapToDataState
import com.abdelrahman.feature_character_details_data.remotedatasource.ICharacterDetailsRemoteDataSource
import com.abdelrahman.feature_character_domain.models.CharacterDetailsSection
import com.abdelrahman.feature_character_domain.models.CharacterDetailsSectionListItem
import com.abdelrahman.feature_character_domain.models.ContentType
import com.abdelrahman.feature_character_domain.models.getCharacterDetailsSection
import com.abdelrahman.feature_character_domain.repository.ICharacterDetailsRepository
import com.abdelrahman.shared_data.models.ImageSpecs
import com.abdelrahman.shared_data.models.createValidThumbnailURL
import com.abdelrahman.shared_domain.models.DataState
import com.abdelrahman.shared_domain.models.Image
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterDetailsRepository @Inject constructor(
    private val iCharacterDetailsRemoteDataSource: ICharacterDetailsRemoteDataSource
) : ICharacterDetailsRepository {

    override suspend fun loadDynamicDetailsContent(
        url: String?,
        contentType: ContentType
    ): Flow<DataState<CharacterDetailsSection>> {
        val characterDetailsSection =
            iCharacterDetailsRemoteDataSource.loadDynamicDetailsContent(url).mapToDataState {
                val characterDetailsSectionListItems = it?.data?.results?.map { sResult ->
                    val characterDetailsSectionListItem = CharacterDetailsSectionListItem(
                        title = sResult.name,
                        image = Image(
                            landscapeImage = sResult.thumbnail?.createValidThumbnailURL(ImageSpecs.LANDSCAPE_INCREDIBLE),
                            squareImage = sResult.thumbnail?.createValidThumbnailURL(ImageSpecs.STANDARD_AMAZING),
                            portraitImage = sResult.thumbnail?.createValidThumbnailURL(ImageSpecs.PORTRAIT_INCREDIBLE)
                        )
                    )
                    return@map characterDetailsSectionListItem
                } as ArrayList<CharacterDetailsSectionListItem>
                contentType.getCharacterDetailsSection(characterDetailsSectionListItems)
            }
        return characterDetailsSection
    }
}