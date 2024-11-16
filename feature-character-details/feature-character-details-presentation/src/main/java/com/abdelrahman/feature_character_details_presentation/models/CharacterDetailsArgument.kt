package com.abdelrahman.feature_character_details_presentation.models

import com.abdelrahman.shared_domain.models.ExtraData
import com.abdelrahman.shared_domain.models.Image

data class CharacterDetailsArgument(
    val name: String?,
    val description: String?,
    val comicUri: String?,
    val storiesUri: String?,
    val eventsUri: String?,
    val seriesUri: String?,
    val extraData: ArrayList<ExtraData>?,
    val image : Image?
)
