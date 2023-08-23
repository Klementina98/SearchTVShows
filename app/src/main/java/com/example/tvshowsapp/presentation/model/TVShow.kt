package com.example.tvshowsapp.presentation.model

import com.example.tvshowsapp.data.model.Image

data class TVShow(
    val image: Image,
    val language: String?,
    val name: String,
    val officialSite: String?,
    val summary: String?,
    val premiered: String?,
    val genres: List<String>,
    var isFavorite: Boolean = false,
)


