@file:OptIn(ExperimentalMaterial3Api::class)

package com.abdelrahman.feature_characters_presentation.characterslist.ui.characterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.abdelrahman.feature_characters_domain.models.Character
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterslist.CharactersListContract
import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_presentation.LoadingTypes
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    characters: ArrayList<Character>? = arrayListOf(),
    loadingTypes: LoadingTypes = LoadingTypes.NONE,
    listState: LazyListState = rememberLazyListState(),
    onEvent: (CharactersListContract.CharacterEvents) -> Unit = {},
    onPickCharacter: (Character) -> Unit = {}
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    ?: 0
            lastVisibleItemIndex >= (totalItemsCount - 2)
        }
    }
    LaunchedEffect(listState, loadingTypes) {
        snapshotFlow { shouldLoadMore.value }.distinctUntilChanged()
            .filter { it }.collect {
                if (loadingTypes == LoadingTypes.NONE) {
                    onEvent(
                        CharactersListContract.CharacterEvents.GetCharacters(
                            LoadingTypes.PAGING_PROGRESS
                        )
                    )
                }
            }
    }

    if (loadingTypes == LoadingTypes.NORMAL_PROGRESS)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = com.abdelrahman.shared_domain.R.color.black)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    else {
        PullToRefreshBox(
            isRefreshing = loadingTypes == LoadingTypes.PULL_TO_REFRESH,
            onRefresh = { onEvent(CharactersListContract.CharacterEvents.GetCharacters(LoadingTypes.PULL_TO_REFRESH)) }) {
            Column(modifier = modifier) {
                Header(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = colorResource(id = R.color.black))
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    state = listState
                ) {
                    items(characters ?: arrayListOf()) { character ->
                        ItemCharacter(modifier = Modifier.clickable {
                            onPickCharacter.invoke(character)
                        }, character = character)
                    }
                    if (loadingTypes == LoadingTypes.PAGING_PROGRESS)
                        item {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .wrapContentHeight()
                                    .background(color = colorResource(id = com.abdelrahman.shared_domain.R.color.black))
                                    .padding(
                                        vertical = dimensionResource(
                                            id = com.abdelrahman.shared_domain.R.dimen.dimen_8
                                        )
                                    ), contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview_CharactersScreen() {
    CharactersScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black))
    )
}