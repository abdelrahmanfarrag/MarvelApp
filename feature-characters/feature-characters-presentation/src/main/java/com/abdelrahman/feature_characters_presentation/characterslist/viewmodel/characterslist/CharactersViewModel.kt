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

    private fun updateCurrentPage(page: Int) {
        setState {
            copy(
                currentPage = page
            )
        }
    }

    private fun updateNextLoadingPage() {
        if (currentState.currentPage < (currentState.charactersModel?.totalPages ?: 0))
            when (currentState.loadingTypes) {
                LoadingTypes.PAGING_PROGRESS -> updateCurrentPage(
                    currentState.currentPage + 1
                )

                LoadingTypes.PULL_TO_REFRESH, LoadingTypes.NORMAL_PROGRESS -> updateCurrentPage(
                    0
                )

                LoadingTypes.NONE -> updateCurrentPage(currentState.currentPage)
            }
    }

    private fun callGetCharacters(loadingTypes: LoadingTypes) {
        viewModelScope.launch {
            setState {
                copy(
                    loadingTypes = loadingTypes
                )
            }
            updateNextLoadingPage()
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
                    loadingTypes = LoadingTypes.NONE
                )
            }
        }
    }

    private fun onError(errorModel: ErrorModel?) {
        setState {
            copy(
                loadingTypes = LoadingTypes.NONE,
                errorModel = errorModel
            )
        }
    }
}