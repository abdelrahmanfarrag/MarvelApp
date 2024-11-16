package com.abdelrahman.feature_characters_presentation.characterslist.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import com.abdelrahman.feature_characters_presentation.R
import com.abdelrahman.feature_characters_presentation.utils.Constants.DELAY_ONE_SECOND
import kotlinx.coroutines.delay

@Composable
fun SearchField(
    modifier: Modifier,
    placeHolderText: String,
    textValue: String?,
    placeHolderTextColor: Color = colorResource(com.abdelrahman.shared_domain.R.color.black),
    onValueChanged: (String) -> Unit,
    borderColor: Color = colorResource(com.abdelrahman.shared_domain.R.color.black),
    backgroundColor: Color = Color.White,
    onSearch: (String?) -> Unit,
    leadingIcon: @Composable (() -> Unit) = { IconSearchField() },
    cursorColor: Color = Color.Black
) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    var currentQuery by remember { mutableStateOf(textValue) }
    var isFirstLaunch by remember { mutableStateOf(true) }

    LaunchedEffect(currentQuery) {
        if (!isFirstLaunch) {
            delay(DELAY_ONE_SECOND)
            keyBoardController?.hide()
            onSearch(currentQuery)
        }
        isFirstLaunch = false
    }

    InputTextField(
        enabled = true,
        modifier = modifier,
        onValueChanged = { newValue ->
            currentQuery = newValue
            onValueChanged(newValue)
        },
        placeHolderText = placeHolderText,
        textValue = textValue,
        placeHolderTextColor = placeHolderTextColor,
        backgroundColor = backgroundColor,
        leadingIcon = leadingIcon,
        borderColor = borderColor,
        cursorColor = cursorColor,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = androidx.compose.ui.text.input.ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyBoardController?.hide()
                onSearch(textValue)
            }
        )
    )
}