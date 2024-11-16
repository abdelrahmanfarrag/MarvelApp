package com.abdelrahman.feature_character_details_data.di

import com.abdelrahman.feature_character_details_data.apiinterface.CharacterDetailsApiInterface
import com.abdelrahman.feature_character_details_data.remotedatasource.CharacterDetailsRemoteDataSource
import com.abdelrahman.feature_character_details_data.remotedatasource.ICharacterDetailsRemoteDataSource
import com.abdelrahman.feature_character_details_data.repository.CharacterDetailsRepository
import com.abdelrahman.feature_character_domain.repository.ICharacterDetailsRepository
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
abstract class CharacterDetailsDataModule {

    companion object {
        @Provides
        @Singleton
        fun providesCharacterDetailsApiInterface(retrofit: Retrofit): CharacterDetailsApiInterface {
            return retrofit.create()
        }
    }

    @Binds
    @Singleton
    abstract fun bindsCharacterDetailsRemoteDataSource(characterDetailsRemoteDataSource: CharacterDetailsRemoteDataSource): ICharacterDetailsRemoteDataSource


    @Binds
    @Singleton
    abstract fun bindCharacterDetailsRepository(characterDetailsRepository: CharacterDetailsRepository):ICharacterDetailsRepository
}