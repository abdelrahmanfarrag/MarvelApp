package com.abdelrahman.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.abdelrahman.marvelapp.navigation.CharacterDetailsScreen
import com.abdelrahman.marvelapp.navigation.CharactersScreen
import com.abdelrahman.marvelapp.navigation.CharactersSearchScreen
import com.abdelrahman.marvelapp.navigation.routes.characterdetails.ICharacterDetailsNavigation
import com.abdelrahman.marvelapp.navigation.routes.characterdetails.characterDetails
import com.abdelrahman.marvelapp.navigation.routes.characterslist.ICharactersListNavigation
import com.abdelrahman.marvelapp.navigation.routes.characterslist.charactersList
import com.abdelrahman.marvelapp.navigation.routes.characterssearch.ICharacterSearchNavigation
import com.abdelrahman.marvelapp.navigation.routes.characterssearch.characterSearch
import com.abdelrahman.marvelapp.ui.theme.MarvelAppTheme
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
                        charactersList(innerPadding, object : ICharactersListNavigation {
                            override fun onNavigateToSearchScreen() {
                                navController.navigate(CharactersSearchScreen)
                            }

                            override fun onNavigateToCharacterDetails(argument: String) {
                                navController.navigate(
                                    CharacterDetailsScreen(argument)
                                )
                            }

                        })
                        characterSearch(innerPadding, object : ICharacterSearchNavigation {
                            override fun navigateUp() {
                                navController.navigateUp()
                            }

                            override fun navigateToCharacterDetails(argument: String) {
                                navController.navigate(CharacterDetailsScreen(argument))
                            }
                        })
                        characterDetails(innerPadding, object : ICharacterDetailsNavigation {
                            override fun navigateUp() {
                                navController.navigateUp()
                            }
                        })
                    }
                }
            }
        }
    }
}