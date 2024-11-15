package com.abdelrahman.feature_characters_presentation.characterslist.viewmodel

import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.shared_domain.models.ErrorModel
import com.abdelrahman.shared_presentation.LoadingTypes
import com.abdelrahman.shared_presentation.viewmodel.Event
import com.abdelrahman.shared_presentation.viewmodel.SingleUIEvent
import com.abdelrahman.shared_presentation.viewmodel.State

class CharactersListContract {
    data class CharactersUIState(
        val loadingTypes: com.abdelrahman.shared_presentation.LoadingTypes = com.abdelrahman.shared_presentation.LoadingTypes.NONE,
        val charactersModel: CharactersModel? = null,
        val errorModel: ErrorModel? = null
    ) : State

    sealed class CharacterEvents : Event {
        data class GetCharacters(val loadingTypes: com.abdelrahman.shared_presentation.LoadingTypes) : CharacterEvents()
    }

    sealed class CharactersSingleActions : SingleUIEvent
}