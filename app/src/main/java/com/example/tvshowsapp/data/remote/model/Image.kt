package com.example.tvshowsapp.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.tvshowsapp.presentation.model.Image

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "medium")
    val medium: String,
    @Json(name = "original")
    val original: String
)

fun Image.toImageDomain(): Image {
    return Image(
        medium = medium,
        original = original
    )
}