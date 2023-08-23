package com.example.tvshowsapp.presentation.model


sealed class SearchResponseState() {

    object Empty: SearchResponseState()

    object Loading: SearchResponseState()

    data class Error(val errorMessage: String): SearchResponseState()

    data class Success(val tvShows: List<TVShow>): SearchResponseState()

}
