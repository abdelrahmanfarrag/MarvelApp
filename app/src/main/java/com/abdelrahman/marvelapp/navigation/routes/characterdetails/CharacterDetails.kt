package com.abdelrahman.marvelapp.navigation.routes.characterdetails

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abdelrahman.feature_character_details_presentation.ui.CharacterDetailsScreen
import com.abdelrahman.feature_character_details_presentation.viewmodel.CharacterDetailsViewModel
import com.abdelrahman.marvelapp.navigation.CharacterDetailsScreen

fun NavGraphBuilder.characterDetails(
    innerPadding: PaddingValues,
    iCharacterDetailsNavigation: ICharacterDetailsNavigation
) {
    composable<CharacterDetailsScreen> {
        val viewModel = hiltViewModel<CharacterDetailsViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle().value
        CharacterDetailsScreen(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize(),
            characterDetailsData = state.characterDetailsData,
            errorModel = state.errorModel,
            loadingTypes = state.loadingTypes, onEvent = { viewModel.sendEvent(it) }) {
            iCharacterDetailsNavigation.navigateUp()
        }
    }

}