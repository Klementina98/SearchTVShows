package com.example.tvshowsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.tvshowsapp.data.local.model.TvShowEntity

@Dao
interface TVShowsDAO {

    @Upsert
    @Transaction
    suspend fun saveTVShow(tvShow: TvShowEntity)

    @Delete
    @Transaction
    suspend fun deleteTVShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM tv_shows")
    @Transaction
    suspend fun getAllFavoriteTVShow(): List<TvShowEntity>
}