package com.abdelrahman.feature_characters_domain.models

import com.abdelrahman.shared_domain.models.ExtraData

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modifiedDate: String?,
    val comicsUri: String?,
    val seriesUri: String?,
    val storiesUri: String?,
    val eventsUri: String?,
    val extraData: ArrayList<ExtraData>?,
    val image: Image?
)
