package com.example.tvshowsapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tvshowsapp.presentation.model.Image
import com.example.tvshowsapp.presentation.model.TVShow

@Entity(tableName = "tv_shows")
data class TvShowEntity(
    @PrimaryKey
    val id: Int,
    val image: String?,
    val language: String?,
    val name: String,
    val premiered: String?,
    val runtime: Int?,
    val status: String,
    val summary: String?,
    val isFavorite: Boolean,
)

fun TvShowEntity.toTVShowDomain(): TVShow{
    return TVShow(
        id = id,
        image = image?.let { Image(medium = it, original = image) },
        language = language,
        name = name,
        status = status,
        runtime = runtime,
        summary = summary,
        premiered = premiered,
        isFavorite = isFavorite,
        genres = emptyList()
    )
}


