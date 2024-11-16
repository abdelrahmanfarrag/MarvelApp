package com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterssearch

import androidx.lifecycle.viewModelScope
import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.feature_characters_domain.usecase.getcharacters.IGetCharactersUseCase
import com.abdelrahman.shared_domain.models.DataState
import com.abdelrahman.shared_domain.models.ErrorModel
import com.abdelrahman.shared_presentation.ui.LoadingTypes
import com.abdelrahman.shared_presentation.utils.Constants.PAGE_SIZE
import com.abdelrahman.shared_presentation.viewmodel.MviBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersSearchViewModel @Inject constructor(
    private val iGetCharactersUseCase: IGetCharactersUseCase
) : MviBaseViewModel<CharactersSearchContract.CharactersSearchState, CharactersSearchContract.CharactersSearchEvents, CharactersSearchContract.SearchSingleEvents>() {

    override fun createInitialState(): CharactersSearchContract.CharactersSearchState {
        return CharactersSearchContract.CharactersSearchState()
    }

    override fun onEvent(event: CharactersSearchContract.CharactersSearchEvents) {
        when (event) {
            is CharactersSearchContract.CharactersSearchEvents.UpdateTypedText -> {
                setState {
                    copy(typedText = event.typedText)
                }
            }

            CharactersSearchContract.CharactersSearchEvents.SearchForResult -> callGetCharacters()
        }
    }

    private fun callGetCharacters() {
        if (!currentState.typedText.isNullOrEmpty() && !currentState.typedText.isNullOrBlank()) {
            viewModelScope.launch {
                setState {
                    copy(
                        loadingTypes = LoadingTypes.NORMAL_PROGRESS,
                        errorModel = null
                    )
                }
                iGetCharactersUseCase(
                    currentState.currentPage,
                    currentState.typedText,
                    PAGE_SIZE
                ).collect { result ->
                    when (result) {
                        is DataState.Error -> onError(result.errorModel)
                        is DataState.Success -> onSuccess(result.data)
                    }
                }
            }
        }
    }

    private fun onSuccess(data: CharactersModel?) {
        setState {
            copy(
                charactersModel = data,
                loadingTypes = LoadingTypes.NONE
            )
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