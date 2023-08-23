package com.example.tvshowsapp.presentation.favorites

import androidx.lifecycle.ViewModel
import com.example.tvshowsapp.presentation.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(): ViewModel() {

    private val _favoriteTVShows = MutableStateFlow<List<TVShow>>(emptyList())
    val favoriteTVShows: StateFlow<List<TVShow>> = _favoriteTVShows

    fun toggleFavorite(tvShow: TVShow) {
        val updatedList = _favoriteTVShows.value.toMutableList()
        if (tvShow.isFavorite) {
            updatedList.remove(tvShow)
        } else {
            updatedList.add(tvShow)
        }
        tvShow.isFavorite = !tvShow.isFavorite
        _favoriteTVShows.value = updatedList
    }
}