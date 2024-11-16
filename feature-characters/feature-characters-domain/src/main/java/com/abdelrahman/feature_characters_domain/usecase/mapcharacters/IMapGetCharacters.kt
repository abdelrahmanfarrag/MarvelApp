package com.abdelrahman.feature_characters_domain.usecase.mapcharacters

import com.abdelrahman.feature_characters_domain.models.CharactersModel
import com.abdelrahman.shared_domain.models.DataState

interface IMapGetCharacters {
    suspend operator fun invoke(dataState: DataState<CharactersModel>): DataState<CharactersModel>
}