package com.abdelrahman.feature_characters_presentation.characterslist.ui.charactersearch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_presentation.ui.SearchField
import com.abdelrahman.feature_characters_presentation.characterslist.viewmodel.characterssearch.CharactersSearchContract

@Composable
fun SearchHeader(
    modifier: Modifier = Modifier,
    value: String? = "",
    onCancelClick: () -> Unit = {},
    onSearch: (CharactersSearchContract.CharactersSearchEvents) -> Unit = {}
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        SearchField(
            modifier = Modifier
                .weight(4f),
            onValueChanged = {
                onSearch.invoke(
                    CharactersSearchContract.CharactersSearchEvents.UpdateTypedText(it)
                )
            },
            placeHolderText = stringResource(id = R.string.search),
            textValue = value,
            placeHolderTextColor = colorResource(R.color.red),
            borderColor = colorResource(R.color.black),
            backgroundColor = Color.White,
            onSearch = {
                onSearch.invoke(
                    CharactersSearchContract.CharactersSearchEvents.SearchForResult
                )
            }
        )
        Text(modifier = Modifier
            .clickable {
                onCancelClick()
            }
            .weight(1f)
            .padding(start = dimensionResource(id = R.dimen.dimen_16)),
            text = stringResource(id = R.string.cancel),
            color = colorResource(id = R.color.red))
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview_SearchHeader() {
    SearchHeader(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    )
}