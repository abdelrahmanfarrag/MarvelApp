package com.abdelrahman.feature_characters_presentation.characterslist.ui.charactersearch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.abdelrahman.feature_characters_domain.models.Character
import com.abdelrahman.shared_presentation.ui.ErrorLayout
import com.abdelrahman.shared_domain.R
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterssearch.CharactersSearchContract
import com.abdelrahman.shared_presentation.ui.AppDefaultProgressbar
import com.abdelrahman.shared_presentation.ui.LoadingTypes

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchState: CharactersSearchContract.CharactersSearchState? = null,
    onCancelClick: () -> Unit = {},
    onCharacterClick: (Character?) -> Unit = {},
    onEvent: (CharactersSearchContract.CharactersSearchEvents) -> Unit = {}
) {
    Column(modifier = modifier) {
        SearchHeader(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = colorResource(
                        id = R.color.black
                    )
                )
                .padding(vertical = dimensionResource(id = R.dimen.dimen_16)),
            onSearch = onEvent,
            onCancelClick = onCancelClick,
            value = searchState?.typedText
        )
        if (searchState?.loadingTypes == LoadingTypes.NORMAL_PROGRESS) {
            AppDefaultProgressbar(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.black))
            )
        } else {
            if (searchState?.errorModel != null)
                ErrorLayout(modifier = Modifier.weight(1f), errorModel = searchState.errorModel) {
                    onEvent(CharactersSearchContract.CharactersSearchEvents.SearchForResult)
                }
            else
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(searchState?.charactersModel?.characters ?: arrayListOf()) { character ->
                        ItemCharacterSearch(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(colorResource(id = R.color.lighterGrey))
                                .clickable {
                                    onCharacterClick.invoke(character)
                                },
                            character = character
                        )
                    }
                }
        }
    }

}