package com.abdelrahman.feature_characters_data.di

import com.abdelrahman.feature_characters_data.api.CharactersApiInterface
import com.abdelrahman.feature_characters_data.remotedatasource.CharactersRemoteDataSource
import com.abdelrahman.feature_characters_data.remotedatasource.ICharactersRemoteDataSource
import com.abdelrahman.feature_characters_data.repository.CharactersRepository
import com.abdelrahman.feature_characters_domain.repository.ICharactersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersDataModule {

    companion object {
        @Provides
        @Singleton
        fun providesCharactersApiInterface(retrofit: Retrofit): CharactersApiInterface {
            return retrofit.create()
        }
    }

    @Binds
    @Singleton
    abstract fun bindsCharactersRemoteDataSource(charactersRemoteDataSource: CharactersRemoteDataSource): ICharactersRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCharactersRepository(charactersRepository: CharactersRepository): ICharactersRepository
}