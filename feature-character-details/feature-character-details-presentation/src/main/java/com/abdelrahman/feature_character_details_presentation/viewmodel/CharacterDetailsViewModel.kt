package com.abdelrahman.feature_character_details_presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.abdelrahman.feature_character_details_presentation.models.CharacterDetailsArgument
import com.abdelrahman.feature_character_details_presentation.models.CharacterDetailsData
import com.abdelrahman.feature_character_domain.models.ContentType
import com.abdelrahman.feature_character_domain.usecase.mergedynamiccontentusecase.IMergeDynamicContentUseCase
import com.abdelrahman.shared_domain.models.DataState
import com.abdelrahman.shared_presentation.ui.LoadingTypes
import com.abdelrahman.shared_presentation.utils.convertJsonArgumentToClass
import com.abdelrahman.shared_presentation.viewmodel.MviBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val iMergeDynamicContentUseCase: IMergeDynamicContentUseCase,
    private val mSavedStateHandle: SavedStateHandle
) : MviBaseViewModel<CharacterDetailsContract.CharacterDetailsState, CharacterDetailsContract.CharacterDetailsEvents, CharacterDetailsContract.SingleAction>() {

    init {
        sendEvent(CharacterDetailsContract.CharacterDetailsEvents.PageOpened)
    }


    override fun createInitialState(): CharacterDetailsContract.CharacterDetailsState {
        return CharacterDetailsContract.CharacterDetailsState()
    }

    override fun onEvent(event: CharacterDetailsContract.CharacterDetailsEvents) {
        when (event) {
            CharacterDetailsContract.CharacterDetailsEvents.PageOpened -> setCharacterDetailsArgument()
            CharacterDetailsContract.CharacterDetailsEvents.CallGetCharacterDetailsImage -> callGetDynamicContent()
        }
    }

    private fun setCharacterDetailsArgument() {
        val characterDetailsArgument =
            mSavedStateHandle.get<String>("characterDetailsArgumentsGson")
                ?.convertJsonArgumentToClass<CharacterDetailsArgument?>()
        setState {
            copy(
                characterDetailsArgument = characterDetailsArgument,
                characterDetailsData = CharacterDetailsData(
                    name = characterDetailsArgument?.name,
                    description = characterDetailsArgument?.description,
                    characterDetailsSections = null,
                    extraData = characterDetailsArgument?.extraData,
                    image = characterDetailsArgument?.image
                )
            )
        }
        sendEvent(CharacterDetailsContract.CharacterDetailsEvents.CallGetCharacterDetailsImage)
    }

    private fun getCharacterDetailsArguments(): Map<ContentType, String?> {
        return mapOf(
            ContentType.STORIES to currentState.characterDetailsArgument?.storiesUri,
            ContentType.SERIES to currentState.characterDetailsArgument?.seriesUri,
            ContentType.COMICS to currentState.characterDetailsArgument?.comicUri,
            ContentType.EVENTS to currentState.characterDetailsArgument?.eventsUri
        )
    }

    private fun callGetDynamicContent() {
        viewModelScope.launch {
            setState {
                copy(
                    loadingTypes = LoadingTypes.NORMAL_PROGRESS, errorModel = null
                )
            }
            iMergeDynamicContentUseCase(getCharacterDetailsArguments()).collect { result ->
                when (result) {
                    is DataState.Error -> setState {
                        copy(
                            errorModel = result.errorModel, loadingTypes = LoadingTypes.NONE
                        )
                    }

                    is DataState.Success -> setState {
                        copy(
                            loadingTypes = LoadingTypes.NONE,
                            characterDetailsData = characterDetailsData?.copy(
                                characterDetailsSections = result.data
                            )
                        )
                    }
                }
            }
        }
    }
}
