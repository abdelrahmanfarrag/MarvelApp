package com.abdelrahman.marvelapp.navigation.routes.characterssearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abdelrahman.feature_character_details_presentation.models.CharacterDetailsArgument
import com.abdelrahman.feature_characters_presentation.characterslist.ui.charactersearch.SearchScreen
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterssearch.CharactersSearchViewModel
import com.abdelrahman.marvelapp.navigation.CharactersSearchScreen
import com.abdelrahman.shared_presentation.utils.toJson

fun NavGraphBuilder.characterSearch(
    innerPadding: PaddingValues,
    iCharacterSearchNavigation: ICharacterSearchNavigation
) {
    composable<CharactersSearchScreen> {
        val searchViewModel = hiltViewModel<CharactersSearchViewModel>()
        val state = searchViewModel.state.collectAsStateWithLifecycle().value
        SearchScreen(modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding())
            .background(
                color = colorResource(
                    id = com.abdelrahman.shared_domain.R.color.darkGrey
                )
            ),
            searchState = state,
            onCharacterClick = { character ->
                val characterArguments = CharacterDetailsArgument(
                    character.name,
                    character.description,
                    character.comicsUri,
                    character.storiesUri,
                    character.eventsUri,
                    character.seriesUri,
                    character.extraData,
                    character.image
                )
                iCharacterSearchNavigation.navigateToCharacterDetails(characterArguments.toJson())
            },
            onCancelClick = { iCharacterSearchNavigation.navigateUp() }) { event ->
            searchViewModel.sendEvent(event)
        }
    }

}