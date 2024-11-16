package com.abdelrahman.feature_character_details_presentation.viewmodel

import com.abdelrahman.feature_character_details_presentation.models.CharacterDetailsArgument
import com.abdelrahman.feature_character_details_presentation.models.CharacterDetailsData
import com.abdelrahman.shared_domain.models.ErrorModel
import com.abdelrahman.shared_presentation.ui.LoadingTypes
import com.abdelrahman.shared_presentation.viewmodel.Event
import com.abdelrahman.shared_presentation.viewmodel.SingleUIEvent
import com.abdelrahman.shared_presentation.viewmodel.State

class CharacterDetailsContract {

    data class CharacterDetailsState(
        val loadingTypes: LoadingTypes = LoadingTypes.NONE,
        val characterDetailsArgument: CharacterDetailsArgument? = null,
        val characterDetailsData: CharacterDetailsData? = null,
        val errorModel: ErrorModel? = null
    ) : State

    sealed class CharacterDetailsEvents : Event {
        data object PageOpened : CharacterDetailsEvents()
        data object CallGetCharacterDetailsImage : CharacterDetailsEvents()
    }

    sealed class SingleAction : SingleUIEvent
}