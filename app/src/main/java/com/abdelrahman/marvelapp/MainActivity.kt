package com.abdelrahman.marvelapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdelrahman.feature_character_details_presentation.models.CharacterDetailsArgument
import com.abdelrahman.feature_character_details_presentation.ui.CharacterDetailsScreen
import com.abdelrahman.feature_character_details_presentation.viewmodel.CharacterDetailsViewModel
import com.abdelrahman.feature_characters_presentation.characterslist.ui.characterlist.CharactersScreen
import com.abdelrahman.feature_characters_presentation.characterslist.ui.charactersearch.SearchScreen
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterslist.CharactersListContract
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterslist.CharactersViewModel
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterssearch.CharactersSearchViewModel
import com.abdelrahman.marvelapp.navigation.CharacterDetailsScreen
import com.abdelrahman.marvelapp.navigation.CharactersScreen
import com.abdelrahman.marvelapp.navigation.CharactersSearchScreen
import com.abdelrahman.marvelapp.ui.theme.MarvelAppTheme
import com.abdelrahman.shared_domain.utils.defaultString
import com.abdelrahman.shared_presentation.utils.toJson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.BLACK
            )
        )
        setContent {
            MarvelAppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()

                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier.padding(
                            bottom = innerPadding.calculateBottomPadding()
                        ), navController = navController, startDestination = CharactersScreen
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
                                .padding(top = innerPadding.calculateTopPadding())
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
                                    navController.navigate(CharactersSearchScreen)
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
                                navController.navigate(
                                    CharacterDetailsScreen(characterArguments.toJson())
                                )
                            }
                        }
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
                                    navController.navigate(CharacterDetailsScreen(characterArguments.toJson()))
                                },
                                onCancelClick = { navController.navigateUp() }) { event ->
                                searchViewModel.sendEvent(event)
                            }
                        }
                        composable<CharacterDetailsScreen> {
                            val viewModel = hiltViewModel<CharacterDetailsViewModel>()
                            val state = viewModel.state.collectAsStateWithLifecycle().value
                            CharacterDetailsScreen(
                                modifier = Modifier
                                    .padding(top = innerPadding.calculateTopPadding())

                                    .fillMaxSize(),
                                characterDetailsData = state.characterDetailsData,
                                loadingTypes = state.loadingTypes
                            ) {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}