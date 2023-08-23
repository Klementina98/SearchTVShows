package com.example.tvshowsapp.domain

import com.example.tvshowsapp.data.model.TVShow

interface TVShowsRepository {

    suspend fun getTVShows(query: String): List<TVShow>
}