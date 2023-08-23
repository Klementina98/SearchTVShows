package com.example.tvshowsapp.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.tvshowsapp.presentation.model.TVShow

@JsonClass(generateAdapter = true)
data class Show(
    @Json(name = "ended")
    val ended: String?,
    @Json(name = "genres")
    val genres: List<String>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: Image?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "officialSite")
    val officialSite: String?,
    @Json(name = "premiered")
    val premiered: String?,
    @Json(name = "rating")
    val rating: Rating,
    @Json(name = "runtime")
    val runtime: Int?,
    @Json(name = "status")
    val status: String,
    @Json(name = "summary")
    val summary: String?,
    @Json(name = "url")
    val url: String,
)

fun Show.toDomainTVShow(): TVShow {
    return TVShow(
        id = id,
        image = image?.let { com.example.tvshowsapp.presentation.model.Image(medium = it.medium, original = image.original) },
        language = language,
        name = name,
        summary = summary,
        premiered = premiered,
        genres = genres,
        runtime = runtime,
        status = status,
    )
}
