package com.example.tvshowsapp.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TVShow(
    @Json(name = "score")
    val score: Double,
    @Json(name = "show")
    val show: Show
)