package com.abdelrahman.marvelapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdelrahman.feature_characters_presentation.characterslist.ui.CharactersScreen
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.CharactersListContract
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.CharactersViewModel
import com.abdelrahman.marvelapp.navigation.CharactersScreen
import com.abdelrahman.marvelapp.ui.theme.MarvelAppTheme
import com.abdelrahman.shared_presentation.LoadingTypes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

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
                        ),
                        navController = navController,
                        startDestination = CharactersScreen
                    ) {
                        composable<CharactersScreen> {
                            val charactersViewModel = hiltViewModel<CharactersViewModel>()
                            val charactersState =
                                charactersViewModel.state.collectAsStateWithLifecycle().value
                            val listState = rememberLazyListState()
                            CharactersScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = innerPadding.calculateTopPadding())
                                    .background(
                                        color = colorResource(
                                            id = R.color.black
                                        )
                                    ),
                                characters = charactersState.charactersModel?.characters,
                                loadingTypes = charactersState.loadingTypes,
                                listState = listState, onEvent = {
                                    charactersViewModel.sendEvent(it)
                                }) { character ->

                            }
                        }
                    }
                }
            }
        }
    }
}