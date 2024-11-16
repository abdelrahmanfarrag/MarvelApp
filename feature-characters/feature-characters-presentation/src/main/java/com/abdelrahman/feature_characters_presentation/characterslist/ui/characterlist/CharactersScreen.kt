package com.abdelrahman.feature_characters_presentation.characterslist.ui.characterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.abdelrahman.feature_characters_domain.models.Character
import com.abdelrahman.shared_presentation.ui.ErrorLayout
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterslist.CharactersListContract
import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_domain.models.ErrorModel
import com.abdelrahman.shared_presentation.models.IPagingComponentInteractions
import com.abdelrahman.shared_presentation.models.PagingComponentModel
import com.abdelrahman.shared_presentation.ui.LoadingTypes
import com.abdelrahman.shared_presentation.ui.AppDefaultProgressbar
import com.abdelrahman.shared_presentation.ui.PagingLazyList

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    pagingComponentModel: PagingComponentModel<Character> = PagingComponentModel(
        iPagingComponentInteractions = object : IPagingComponentInteractions {
            override fun onRequestNextPage() {}
            override fun onPullToRefresh() {}
        }),
    loadingTypes: LoadingTypes = LoadingTypes.NONE,
    errorModel: ErrorModel? = null,
    onEvent: (CharactersListContract.CharacterEvents) -> Unit = {},
    onSearchClick: () -> Unit = {},
    onPickCharacter: (Character) -> Unit = {}
) {
    if (loadingTypes == LoadingTypes.NORMAL_PROGRESS) AppDefaultProgressbar(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black)),
    )
    else {
        if (errorModel != null) ErrorLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(colorResource(id = R.color.black)), errorModel = errorModel
        ) {
            onEvent.invoke(
                CharactersListContract.CharacterEvents.GetCharacters(
                    LoadingTypes.NORMAL_PROGRESS
                )
            )
        }
        else PagingLazyList(modifier = modifier, pagingComponentModel,
            extraContent = {
                Header(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = colorResource(id = R.color.black))
                ) {
                    onSearchClick()
                }
            }) { character ->
            ItemCharacter(modifier = Modifier.clickable {
                onPickCharacter.invoke(character)
            }, character = character)

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