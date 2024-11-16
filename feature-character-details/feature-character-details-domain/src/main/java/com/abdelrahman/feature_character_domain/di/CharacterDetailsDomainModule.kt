package com.abdelrahman.feature_character_domain.di

import com.abdelrahman.feature_character_domain.usecase.ILoadDynamicContentUseCase
import com.abdelrahman.feature_character_domain.usecase.LoadDynamicContentUseCase
import com.abdelrahman.feature_character_domain.usecase.mergedynamiccontentusecase.IMergeDynamicContentUseCase
import com.abdelrahman.feature_character_domain.usecase.mergedynamiccontentusecase.MergeDynamicContentTypeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CharacterDetailsDomainModule {

    @Binds
    @ViewModelScoped
    abstract fun bindLoadDynamicContentUseCase(loadDynamicContentUseCase: LoadDynamicContentUseCase): ILoadDynamicContentUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindMergeDynamicContentTypeUseCase(mergeDynamicContentTypeUseCase: MergeDynamicContentTypeUseCase): IMergeDynamicContentUseCase
}