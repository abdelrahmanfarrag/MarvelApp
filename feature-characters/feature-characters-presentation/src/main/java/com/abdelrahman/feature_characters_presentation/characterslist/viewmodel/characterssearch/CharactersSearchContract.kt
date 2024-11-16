package com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterssearch

import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.shared_domain.models.ErrorModel
import com.abdelrahman.shared_presentation.LoadingTypes
import com.abdelrahman.shared_presentation.viewmodel.Event
import com.abdelrahman.shared_presentation.viewmodel.SingleUIEvent
import com.abdelrahman.shared_presentation.viewmodel.State

class CharactersSearchContract {

    data class CharactersSearchState(
        val loadingTypes: LoadingTypes = LoadingTypes.NONE,
        val charactersModel: CharactersModel? = null,
        val errorModel: ErrorModel? = null,
        val typedText: String ?= "",
        val currentPage:Int = 0
    ) : State

    sealed class CharactersSearchEvents : Event {
        data class LoadData(val searchKey: String?, val loadingTypes: LoadingTypes) : CharactersSearchEvents()
        data class UpdateTypedText(val typedText:String?):CharactersSearchEvents()
        data object SearchForResult : CharactersSearchEvents()
    }

    sealed class SearchSingleEvents : SingleUIEvent
}