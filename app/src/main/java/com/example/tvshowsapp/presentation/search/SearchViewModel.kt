package com.example.tvshowsapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsapp.data.remote.model.toDomainTVShow
import com.example.tvshowsapp.domain.FavoriteTVShowsRepository
import com.example.tvshowsapp.domain.TVShowsRepository
import com.example.tvshowsapp.presentation.model.SearchResponseState
import com.example.tvshowsapp.presentation.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val favoriteTVShowsRepository: FavoriteTVShowsRepository,
) : ViewModel() {
    private val _tvShowsState = MutableStateFlow<SearchResponseState>(SearchResponseState.Loading)
    val tvShowsState: StateFlow<SearchResponseState> = _tvShowsState

    private val _favoriteTVShows = MutableStateFlow<List<TVShow>>(emptyList())

    init {
        getFavorites()
        fetchTVShows("girls")
    }

    private fun fetchTVShows(query: String) {
        _tvShowsState.value = SearchResponseState.Loading
        viewModelScope.launch {
            try {
                val tvShowsRemote = tvShowsRepository.getTVShows(query = query)

                val tvShowsToDisplay = checkIfFavorite(tvShowsRemote.map { it.show.toDomainTVShow() })

                _tvShowsState.value =
                    SearchResponseState.Success(tvShows = tvShowsToDisplay)
            } catch (e: Exception) {
                _tvShowsState.value =
                    SearchResponseState.Error(errorMessage = e.message ?: "Unknown error")
            }
        }
    }

    private fun checkIfFavorite(tvShowsToDisplay: List<TVShow>): List<TVShow> {
        // we need to check if the user have favorites from previous session
        tvShowsToDisplay.forEach { tvShow ->
            _favoriteTVShows.value.forEach{ favorite->
                if (tvShow.id == favorite.id){
                    tvShow.isFavorite = true
                }

            }
        }
        return tvShowsToDisplay
    }

    private fun getFavorites() {
        viewModelScope.launch {
            _favoriteTVShows.value = favoriteTVShowsRepository.getAllFavoriteTVShow()
        }
    }
    fun updateFavorite(tvShow: TVShow) {
        viewModelScope.launch {
            if (tvShow.isFavorite) {
                favoriteTVShowsRepository.removeFavoriteTvShow(tvShow = tvShow)
            } else {
                favoriteTVShowsRepository.saveFavoriteTVShow(tvShow = tvShow)
            }
            tvShow.isFavorite = !tvShow.isFavorite
        }
    }

    fun onClickSearchButton(query: String) {
        fetchTVShows(query = query)
    }

}


