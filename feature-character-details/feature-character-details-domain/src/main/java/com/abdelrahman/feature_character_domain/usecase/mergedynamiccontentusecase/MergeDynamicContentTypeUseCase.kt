package com.abdelrahman.feature_character_domain.usecase.mergedynamiccontentusecase

import com.abdelrahman.feature_character_domain.models.CharacterDetailsSection
import com.abdelrahman.feature_character_domain.models.ContentType
import com.abdelrahman.feature_character_domain.usecase.ILoadDynamicContentUseCase
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MergeDynamicContentTypeUseCase @Inject constructor(
    private val iLoadDynamicContentUseCase: ILoadDynamicContentUseCase
) : IMergeDynamicContentUseCase {

    override suspend fun invoke(contentMap: Map<ContentType, String?>): Flow<DataState<ArrayList<CharacterDetailsSection?>>> {
        val eventsUseCase =
            iLoadDynamicContentUseCase(ContentType.EVENTS, contentMap[ContentType.EVENTS])
        val comicsUseCase =
            iLoadDynamicContentUseCase(ContentType.COMICS, contentMap[ContentType.COMICS])
        val storiesUseCase =
            iLoadDynamicContentUseCase(ContentType.STORIES, contentMap[ContentType.STORIES])
        val seriesUseCase =
            iLoadDynamicContentUseCase(ContentType.SERIES, contentMap[ContentType.SERIES])

        val result = eventsUseCase.zip(comicsUseCase) { events, comics ->
            return@zip if (events is DataState.Success && comics is DataState.Success)
                DataState.Success<ArrayList<CharacterDetailsSection?>>(
                    data = arrayListOf(
                        events.data,
                        comics.data
                    )
                )
            else if (events is DataState.Success)
                DataState.Success<ArrayList<CharacterDetailsSection?>>(
                    data = arrayListOf(
                        events.data,
                    )
                )
            else if (comics is DataState.Success)
                DataState.Success<ArrayList<CharacterDetailsSection?>>(
                    data = arrayListOf(
                        comics.data,
                    )
                )
            else
                events as DataState<ArrayList<CharacterDetailsSection?>>
        }.zip(storiesUseCase) { comicsAndEvents, stories ->
            return@zip if (comicsAndEvents is DataState.Success && stories is DataState.Success)
                comicsAndEvents.copy(
                    data = comicsAndEvents.data?.apply {
                       add(stories.data)
                    }
                )
            else
                comicsAndEvents
        }.zip(seriesUseCase) { comicsEventsAndStories, series ->
            return@zip if (comicsEventsAndStories is DataState.Success && series is DataState.Success)
                comicsEventsAndStories.copy(
                    data = comicsEventsAndStories.data?.apply {
                        add(series.data)
                    }
                )
            else
                comicsEventsAndStories
        }.map { zipResult ->
            return@map if (zipResult is DataState.Success)
                DataState.Success(data = zipResult.data?.filter {
                    !it?.sectionsList.isNullOrEmpty()
                } as ArrayList)
            else
                zipResult
        }
        return result
    }
}