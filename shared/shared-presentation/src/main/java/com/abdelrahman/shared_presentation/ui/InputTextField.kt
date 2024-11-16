package com.abdelrahman.shared_presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.Container
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_domain.utils.defaultString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    showError: Boolean = false,
    enabled: Boolean,
    errorMessage: String = "",
    borderErrorColor: Color = colorResource(R.color.black),
    textErrorColor: Color = colorResource(id = R.color.red),
    modifier: Modifier,
    onValueChanged: (String) -> Unit,
    placeHolderText: String,
    textValue: String?,
    placeHolderTextColor: Color,
    singleLine: Boolean = true,
    focusedBorderThickness: Dp = dimensionResource(id = R.dimen.dimen_1),
    unfocusedBorderThickness: Dp = dimensionResource(id = R.dimen.dimen_1),
    borderColor: Color = colorResource(R.color.black),
    maxLines: Int = 1,
    backgroundColor: Color = Color.White,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    topStartRoundedCorner: Dp = dimensionResource(id = R.dimen.dimen_16),
    topEndRoundedCorner: Dp = dimensionResource(id = R.dimen.dimen_16),
    bottomStartRoundedCorner: Dp = dimensionResource(id = R.dimen.dimen_16),
    bottomEndRoundedCorner: Dp = dimensionResource(id = R.dimen.dimen_16),
    cursorColor: Color = Color.Black,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8))
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    backgroundColor,
                    RoundedCornerShape(
                        topStart = topStartRoundedCorner,
                        topEnd = topEndRoundedCorner,
                        bottomStart = bottomStartRoundedCorner,
                        bottomEnd = bottomEndRoundedCorner
                    )
                ),
            value = textValue.defaultString(),
            onValueChange = {
                onValueChanged.invoke(it)
            },
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            cursorBrush = SolidColor(cursorColor),

            )
        { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = textValue.defaultString(),
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = placeHolderText, color = placeHolderTextColor
                    )
                },
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(id = R.dimen.dimen_16),
                    vertical = dimensionResource(id = R.dimen.dimen_16)
                ),
                container = {
                    Container(
                        enabled = enabled,
                        isError = showError,
                        interactionSource = interactionSource,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (showError) borderErrorColor else borderColor,
                            unfocusedBorderColor = borderColor,
                            errorBorderColor = borderErrorColor
                        ),
                        shape = RoundedCornerShape(
                            topStart = topStartRoundedCorner,
                            topEnd = topEndRoundedCorner,
                            bottomStart = bottomStartRoundedCorner,
                            bottomEnd = bottomEndRoundedCorner
                        ),
                        focusedBorderThickness = focusedBorderThickness,
                        unfocusedBorderThickness = unfocusedBorderThickness,
                    )
                },
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon

            )
        }
        if (showError && errorMessage.isNotEmpty()) {
            Text(
                modifier = Modifier.background(Color.Transparent),
                text = errorMessage,
                color = textErrorColor
            )
        }
    }
}