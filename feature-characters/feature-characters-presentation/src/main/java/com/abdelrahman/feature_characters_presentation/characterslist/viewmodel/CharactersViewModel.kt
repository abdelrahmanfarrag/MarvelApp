package com.abdelrahman.feature_characters_presentation.characterslist.viewmodel

import androidx.lifecycle.viewModelScope
import com.abdelrahman.feature_characters_domain.usecase.getcharacters.IGetCharactersUseCase
import com.abdelrahman.shared_domain.models.DataState
import com.abdelrahman.shared_presentation.LoadingTypes
import com.abdelrahman.shared_presentation.viewmodel.MviBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
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

    private fun callGetCharacters(loadingTypes: LoadingTypes) {
        viewModelScope.launch {
            iGetCharactersUseCase(1, null, 20).onStart {
                setState {
                    copy(
                        loadingTypes = loadingTypes
                    )
                }
            }.collect { result ->
                when (result) {
                    is DataState.Success -> setState {
                        copy(
                            charactersModel = result.data,
                            loadingTypes = LoadingTypes.NONE
                        )
                    }

                    else -> {
                        val resultError = result as DataState.Error
                        setState {
                            copy(
                                errorModel = resultError.errorModel,
                                loadingTypes = LoadingTypes.NONE
                            )
                        }
                    }
                }

            }
        }
    }


}