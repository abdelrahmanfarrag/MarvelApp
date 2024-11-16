package com.abdelrahman.feature_character_details_presentation.models

import com.abdelrahman.feature_character_domain.models.CharacterDetailsSection
import com.abdelrahman.shared_domain.models.ExtraData
import com.abdelrahman.shared_domain.models.Image

data class CharacterDetailsData(
    val name: String?,
    val description: String?,
    val characterDetailsSections: ArrayList<CharacterDetailsSection?>?,
    val extraData: ArrayList<ExtraData>?,
    val image: Image?
)
