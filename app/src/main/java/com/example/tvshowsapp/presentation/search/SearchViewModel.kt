package com.example.tvshowsapp.presentation.search

import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsapp.data.model.toDomainTVShow
import com.example.tvshowsapp.domain.TVShowsRepository
import com.example.tvshowsapp.presentation.model.SearchResponseState
import com.example.tvshowsapp.presentation.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
): ViewModel() {
    private val _tvShowsState = MutableStateFlow<SearchResponseState>(SearchResponseState.Loading)
    val tvShowsState: StateFlow<SearchResponseState> = _tvShowsState

    init {
        fetchTVShows("girls")
    }

    private fun fetchTVShows(query: String) {
        _tvShowsState.value = SearchResponseState.Loading
        viewModelScope.launch {
            try {
                val tvShows = tvShowsRepository.getTVShows(query = query)

                if (tvShows.isNotEmpty()) {
                    _tvShowsState.value = SearchResponseState.Success(tvShows = tvShows.map { it.show.toDomainTVShow() })
                } else {
                    _tvShowsState.value = SearchResponseState.Empty
                }
            } catch (e: Exception) {
                _tvShowsState.value = SearchResponseState.Error(errorMessage = e.message ?: "Unknown error")
            }
        }
    }

    fun onClickSearchButton(query: String) {
        fetchTVShows(query = query)
    }
}


