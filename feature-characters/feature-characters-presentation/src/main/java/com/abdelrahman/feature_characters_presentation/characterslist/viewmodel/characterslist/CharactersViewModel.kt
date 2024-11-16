package com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterslist

import androidx.lifecycle.viewModelScope
import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.feature_characters_domain.usecase.getcharacters.IGetCharactersUseCase
import com.abdelrahman.shared_domain.models.DataState
import com.abdelrahman.shared_domain.models.ErrorModel
import com.abdelrahman.shared_presentation.LoadingTypes
import com.abdelrahman.shared_presentation.viewmodel.MviBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val iGetCharactersUseCase: IGetCharactersUseCase
) : MviBaseViewModel<CharactersListContract.CharactersUIState, CharactersListContract.CharacterEvents, CharactersListContract.CharactersSingleActions>() {

    override fun createInitialState(): CharactersListContract.CharactersUIState {
        return CharactersListContract.CharactersUIState()
    }

    init {
        sendEvent(CharactersListContract.CharacterEvents.GetCharacters(LoadingTypes.NORMAL_PROGRESS))
    }

    override fun onEvent(event: CharactersListContract.CharacterEvents) {
        when (event) {
            is CharactersListContract.CharacterEvents.GetCharacters -> callGetCharacters(event.loadingTypes)
        }
    }

    private fun setCurrentPage(loadingTypes: LoadingTypes) {
        val currentPage = when (loadingTypes) {
            LoadingTypes.NONE -> currentState.currentPage
            LoadingTypes.NORMAL_PROGRESS -> 0
            LoadingTypes.PAGING_PROGRESS -> if (currentState.currentPage < (currentState.charactersModel?.totalPages
                    ?: 0)
            ) currentState.currentPage + 1
            else currentState.currentPage

            LoadingTypes.PULL_TO_REFRESH -> 0
        }
        setState {
            copy(
                currentPage = currentPage
            )
        }
    }

    private fun callGetCharacters(loadingTypes: LoadingTypes) {
        if (currentState.currentPage <= (currentState.charactersModel?.totalPages ?: 0))
            viewModelScope.launch {
                setState {
                    copy(
                        loadingTypes = loadingTypes
                    )
                }
                iGetCharactersUseCase(currentState.currentPage, null, 20).collect { result ->
                    when (result) {
                        is DataState.Error -> onError(result.errorModel)
                        is DataState.Success -> onSuccess(result.data)
                    }
                }
            }
    }

    private fun onSuccess(data: CharactersModel?) {
        val currentLoading = currentState.loadingTypes
        setCurrentPage(currentLoading)
        if (currentLoading == LoadingTypes.NORMAL_PROGRESS || currentLoading == LoadingTypes.PULL_TO_REFRESH) {
            setState {
                copy(
                    charactersModel = data,
                    loadingTypes = LoadingTypes.NONE
                )
            }
        } else if (currentLoading == LoadingTypes.PAGING_PROGRESS) {
            setState {
                copy(
                    charactersModel = charactersModel?.copy(
                        characters = charactersModel.characters?.apply {
                            addAll(data?.characters ?: arrayListOf())
                        }
                    ),
                    loadingTypes = LoadingTypes.NONE,
                    errorModel = null
                )
            }
        }
    }

    private fun onError(errorModel: ErrorModel?) {
        if (currentState.charactersModel?.characters?.isNotEmpty() == true) {
            sendSingleUIEvent {
                CharactersListContract.CharactersSingleActions.ShowToastMessage(
                    errorModel?.errorMessage
                )
            }
            setState {
                copy(
                    loadingTypes = LoadingTypes.NONE
                )
            }
        } else
            setState {
                copy(
                    loadingTypes = LoadingTypes.NONE,
                    errorModel = errorModel
                )
            }
    }
}