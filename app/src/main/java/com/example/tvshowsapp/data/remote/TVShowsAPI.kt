package com.example.tvshowsapp.data.remote

import com.example.tvshowsapp.data.remote.model.TVShow
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowsAPI {
    @GET("search/shows")
    suspend fun getTVShows(@Query("q") query: String): List<TVShow>
}