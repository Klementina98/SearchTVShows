package com.example.tvshowsapp.data

import com.example.tvshowsapp.data.model.TVShow
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowsAPI {

    @GET("search/shows")
    suspend fun getTVShows(@Query("q") query: String): List<TVShow>
}