package com.abdelrahman.feature_characters_domain.di

import com.abdelrahman.feature_characters_domain.usecase.getcharacters.GetCharactersUseCase
import com.abdelrahman.feature_characters_domain.usecase.getcharacters.IGetCharactersUseCase
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
    abstract fun bindGetCharactersUseCase(getCharactersUseCase: GetCharactersUseCase): IGetCharactersUseCase
}