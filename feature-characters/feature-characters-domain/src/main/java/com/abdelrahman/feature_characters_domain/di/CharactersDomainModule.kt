package com.abdelrahman.feature_characters_domain.di

import com.abdelrahman.feature_characters_domain.usecase.getcharacters.GetCharactersUseCase
import com.abdelrahman.feature_characters_domain.usecase.getcharacters.IGetCharactersUseCase
import com.abdelrahman.feature_characters_domain.usecase.mapcharacters.IMapGetCharacters
import com.abdelrahman.feature_characters_domain.usecase.mapcharacters.MapGetCharacters
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CharactersDomainModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMapGetCharacters(mapGetCharacters: MapGetCharacters): IMapGetCharacters

    @Binds
    @ViewModelScoped
    abstract fun bindGetCharactersUseCase(getCharactersUseCase: GetCharactersUseCase): IGetCharactersUseCase
}