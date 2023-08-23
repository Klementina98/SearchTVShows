package com.example.tvshowsapp.presentation.model

import com.example.tvshowsapp.data.local.model.TvShowEntity

data class TVShow(
    val id: Int,
    val image: Image?,
    val language: String?,
    val name: String,
    val status : String,
    val runtime : Int?,
    val summary: String?,
    val premiered: String?,
    val genres: List<String>,
    var isFavorite: Boolean = false,
)

fun TVShow.toTVShowEntity(): TvShowEntity{
    return TvShowEntity(
        id = id,
        image = image?.medium,
        language = language,
        name = name,
        premiered = premiered,
        status = status,
        runtime = runtime,
        summary = summary,
        isFavorite = isFavorite
    )
}


