package com.example.tvshowsapp.di

import com.example.tvshowsapp.data.remote.BASE_URL
import com.example.tvshowsapp.data.remote.TVShowsAPI
import com.example.tvshowsapp.data.remote.TVShowsRepositoryImpl
import com.example.tvshowsapp.domain.TVShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TVShowsModule {

    @Provides
    @Singleton
    fun provideRetrofitAPI(retrofitBuilder: Builder): TVShowsAPI =
        retrofitBuilder
            .build()
            .create(TVShowsAPI::class.java)

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Builder =
        Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())

    @Provides
    @Singleton
    fun provideTVShowsRepository(tvShowsAPI: TVShowsAPI) : TVShowsRepository =
        TVShowsRepositoryImpl(tvShowsAPI = tvShowsAPI)


}