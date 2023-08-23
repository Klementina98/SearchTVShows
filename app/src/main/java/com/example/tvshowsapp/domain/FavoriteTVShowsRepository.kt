package com.example.tvshowsapp.domain

import com.example.tvshowsapp.presentation.model.TVShow

interface FavoriteTVShowsRepository {

    suspend fun saveFavoriteTVShow(tvShow: TVShow)

    suspend fun removeFavoriteTvShow(tvShow: TVShow)

    suspend fun getAllFavoriteTVShow(): List<TVShow>
}