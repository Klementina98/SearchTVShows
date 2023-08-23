package com.example.tvshowsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tvshowsapp.data.local.model.TvShowEntity

@Database(
    entities = [TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TVShowDatabase : RoomDatabase(){

    abstract fun tvShowsDAO(): TVShowsDAO
}