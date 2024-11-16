package com.abdelrahman.feature_characters_data.repository

import com.abdelrahman.api.mappers.mapToDataState
import com.abdelrahman.feature_characters_data.models.GetCharactersRequest
import com.abdelrahman.shared_data.models.createValidThumbnailURL
import com.abdelrahman.feature_characters_data.remotedatasource.ICharactersRemoteDataSource
import com.abdelrahman.shared_data.models.ImageSpecs
import com.abdelrahman.feature_characters_domain.models.Character
import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.shared_domain.models.Image
import com.abdelrahman.feature_characters_domain.repository.ICharactersRepository
import com.abdelrahman.shared_domain.models.DataState
import com.abdelrahman.shared_domain.models.ExtraData
import com.abdelrahman.shared_domain.models.ExtraDataEnum
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Suppress("UNCHECKED_CAST", "LABEL_NAME_CLASH")
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
                        comicsUri = response.comics?.collectionURI,
                        seriesUri = response.series?.collectionURI,
                        storiesUri = response.stories?.collectionURI,
                        eventsUri = response.events?.collectionURI,
                        extraData = response.urls?.map { url ->
                            val type = ExtraDataEnum.entries.toTypedArray().find {
                                it.type == url.type
                            }
                            return@map when (type) {
                                ExtraDataEnum.DETAIL -> ExtraData(
                                    com.abdelrahman.shared_domain.R.string.details,
                                    url.url
                                )

                                ExtraDataEnum.WIKI -> ExtraData(
                                    com.abdelrahman.shared_domain.R.string.wiki,
                                    url.url
                                )

                                ExtraDataEnum.COMIC_LINK -> ExtraData(
                                    com.abdelrahman.shared_domain.R.string.comic_link,
                                    url.url
                                )

                                null -> null
                            }
                        } as ArrayList<ExtraData>,
                        image = Image(
                            landscapeImage = response.thumbnail?.createValidThumbnailURL(ImageSpecs.LANDSCAPE_INCREDIBLE),
                            squareImage = response.thumbnail?.createValidThumbnailURL(ImageSpecs.STANDARD_AMAZING),
                            portraitImage = response.thumbnail?.createValidThumbnailURL(ImageSpecs.PORTRAIT_INCREDIBLE)
                        ),
                    )
                } as ArrayList<Character>)

        }
    }
}