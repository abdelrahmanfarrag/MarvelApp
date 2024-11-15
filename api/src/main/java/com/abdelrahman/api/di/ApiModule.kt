package com.abdelrahman.api.di

import android.content.Context
import android.net.ConnectivityManager
import com.abdelrahman.api.BuildConfig
import com.abdelrahman.api.networkcheck.CheckNetworkAvailability
import com.abdelrahman.api.networkcheck.ICheckNetworkAvailability
import com.abdelrahman.api.retrofit.interceptors.IQueryParamsInterceptor
import com.abdelrahman.api.retrofit.interceptors.QueryParamsInterceptor
import com.abdelrahman.api.validateresponse.IValidateResponse
import com.abdelrahman.api.validateresponse.ValidateResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    companion object {

        @Provides
        @Singleton
        fun providesGson(): Gson {
            return GsonBuilder().create()
        }

        @Provides
        @Singleton
        fun providesConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
            return context.getSystemService(ConnectivityManager::class.java)
        }

        @Provides
        @Singleton
        fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
            return logging
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            iQueryParamsInterceptor: IQueryParamsInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(iQueryParamsInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun providesQuranAPI(
            client: OkHttpClient,
            gson: Gson
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }
    }

    @Binds
    @Singleton
    abstract fun bindsIQueryParamsInterceptor(queryParamsInterceptor: QueryParamsInterceptor): IQueryParamsInterceptor

    @Binds
    @Singleton
    abstract fun bindsNetworkAvailability(checkNetworkAvailability: CheckNetworkAvailability): ICheckNetworkAvailability

    @Binds
    @Singleton
    abstract fun bindsValidateResponse(validateResponse: ValidateResponse): IValidateResponse
}