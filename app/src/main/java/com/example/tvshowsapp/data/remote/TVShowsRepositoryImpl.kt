package com.example.tvshowsapp.data.remote

import com.example.tvshowsapp.data.remote.model.TVShow
import com.example.tvshowsapp.domain.TVShowsRepository
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val tvShowsAPI: TVShowsAPI,
) : TVShowsRepository {

    override suspend fun getTVShows(query: String): List<TVShow> {
        return tvShowsAPI.getTVShows(query)
    }
}