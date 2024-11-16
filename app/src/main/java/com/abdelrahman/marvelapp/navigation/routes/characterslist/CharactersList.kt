package com.abdelrahman.marvelapp.navigation.routes.characterslist

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abdelrahman.feature_character_details_presentation.models.CharacterDetailsArgument
import com.abdelrahman.feature_characters_presentation.characterslist.ui.characterlist.CharactersScreen
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterslist.CharactersListContract
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterslist.CharactersViewModel
import com.abdelrahman.marvelapp.R
import com.abdelrahman.marvelapp.navigation.CharactersScreen
import com.abdelrahman.shared_domain.utils.defaultString
import com.abdelrahman.shared_presentation.utils.toJson

fun NavGraphBuilder.charactersList(
    innerPaddingValues: PaddingValues,
    iCharactersListNavigation: ICharactersListNavigation
) {
    composable<CharactersScreen> {
        val charactersViewModel = hiltViewModel<CharactersViewModel>()
        val charactersState =
            charactersViewModel.state.collectAsStateWithLifecycle().value
        val oneTimeAction =
            charactersViewModel.singleEvent.collectAsStateWithLifecycle(
                initialValue = null
            ).value
        val context = LocalContext.current
        LaunchedEffect(key1 = oneTimeAction) {
            when (oneTimeAction) {
                is CharactersListContract.CharactersSingleActions.ShowToastMessage -> Toast.makeText(
                    context,
                    oneTimeAction.textWrapper?.getStringFromTextWrapper(context)
                        .defaultString(),
                    Toast.LENGTH_LONG
                ).show()

                null -> Unit
            }
        }
        CharactersScreen(modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPaddingValues.calculateTopPadding())
            .background(
                color = colorResource(
                    id = R.color.black
                )
            ),
            pagingComponentModel = charactersState.pagingComponentModel,
            loadingTypes = charactersState.loadingTypes,
            errorModel = charactersState.errorModel,
            onEvent = {
                charactersViewModel.sendEvent(it)
            },
            onSearchClick = {
                iCharactersListNavigation.onNavigateToSearchScreen()
            }) { character ->
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
            iCharactersListNavigation.onNavigateToCharacterDetails(characterArguments.toJson())

        }
    }

}