package com.example.tvshowsapp.data

import com.example.tvshowsapp.data.model.TVShow
import com.example.tvshowsapp.domain.TVShowsRepository
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val tvShowsAPI: TVShowsAPI,
) : TVShowsRepository {

    override suspend fun getTVShows(query: String): List<TVShow> {
        return tvShowsAPI.getTVShows(query)
    }
}