package com.abdelrahman.feature_character_domain.models

import androidx.annotation.StringRes
import com.abdelrahman.feature_character_domain.R

sealed class CharacterDetailsSection(
    @StringRes val sectionTitle: Int,
    val sectionsList: ArrayList<CharacterDetailsSectionListItem>?
) {

    data class Comics(val data: ArrayList<CharacterDetailsSectionListItem>?) :
        CharacterDetailsSection(
            R.string.comics, data
        )

    data class Series(val data: ArrayList<CharacterDetailsSectionListItem>?) :
        CharacterDetailsSection(
            R.string.series, data
        )

    data class Stories(val data: ArrayList<CharacterDetailsSectionListItem>?) :
        CharacterDetailsSection(
            R.string.stories,
            data
        )

    data class Events(val data: ArrayList<CharacterDetailsSectionListItem>?) :
        CharacterDetailsSection(
            R.string.events,
            data
        )

}