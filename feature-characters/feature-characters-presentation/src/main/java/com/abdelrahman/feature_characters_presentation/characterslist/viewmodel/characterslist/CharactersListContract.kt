package com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterslist

import com.abdelrahman.feature_characters_domain.models.Character
import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.shared_domain.models.ErrorModel
import com.abdelrahman.shared_domain.models.TextWrapper
import com.abdelrahman.shared_presentation.models.PagingComponentModel
import com.abdelrahman.shared_presentation.ui.LoadingTypes
import com.abdelrahman.shared_presentation.viewmodel.Event
import com.abdelrahman.shared_presentation.viewmodel.SingleUIEvent
import com.abdelrahman.shared_presentation.viewmodel.State

class CharactersListContract {
    data class CharactersUIState(
        val loadingTypes: LoadingTypes = LoadingTypes.NONE,
        val charactersModel: CharactersModel? = null,
        val errorModel: ErrorModel? = null,
        val currentPage: Int = 0,
        val pagingComponentModel: PagingComponentModel<Character>
    ) : State

    sealed class CharacterEvents : Event {
        data class GetCharacters(val loadingTypes: LoadingTypes) :
            CharacterEvents()
    }

    sealed class CharactersSingleActions : SingleUIEvent {
        data class ShowToastMessage(val textWrapper: TextWrapper?) : CharactersSingleActions()
    }
}