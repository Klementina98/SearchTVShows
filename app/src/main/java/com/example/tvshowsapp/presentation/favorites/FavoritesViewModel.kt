package com.example.tvshowsapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsapp.domain.FavoriteTVShowsRepository
import com.example.tvshowsapp.presentation.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteTVShowsRepository: FavoriteTVShowsRepository,
) : ViewModel() {

    private val _favoriteTVShows = MutableStateFlow<List<TVShow>>(emptyList())
    val favoriteTVShows: StateFlow<List<TVShow>> = _favoriteTVShows

    init {
        readFavoritesTVShows()
    }

    fun readFavoritesTVShows() {
        viewModelScope.launch {
            _favoriteTVShows.value = favoriteTVShowsRepository.getAllFavoriteTVShow()
        }
    }
}