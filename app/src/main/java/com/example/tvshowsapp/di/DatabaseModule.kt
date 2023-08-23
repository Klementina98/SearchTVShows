package com.example.tvshowsapp.di

import android.content.Context
import androidx.room.Room
import com.example.tvshowsapp.data.local.FavoritesTVShowsRepositoryImpl
import com.example.tvshowsapp.data.local.TVShowDatabase
import com.example.tvshowsapp.data.local.TVShowsDAO
import com.example.tvshowsapp.data.remote.TVShowsAPI
import com.example.tvshowsapp.data.remote.TVShowsRepositoryImpl
import com.example.tvshowsapp.domain.FavoriteTVShowsRepository
import com.example.tvshowsapp.domain.TVShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideTVShowsDAO(database: TVShowDatabase): TVShowsDAO = database.tvShowsDAO()

    @Provides
    @Singleton
    fun provideTVShowDatabase(@ApplicationContext context: Context): TVShowDatabase =
        Room.databaseBuilder(
            context = context,
            TVShowDatabase::class.java,
            "tv_show_db"
        ).build()

    @Provides
    @Singleton
    fun provideFavoritesTVShowsRepository(tvShowsDao: TVShowsDAO) : FavoriteTVShowsRepository =
        FavoritesTVShowsRepositoryImpl(favoritesTVShowsDao = tvShowsDao)


}

