package com.example.tvshowsapp.presentation.search

import android.annotation.SuppressLint
import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.tvshowsapp.presentation.model.SearchResponseState
import com.example.tvshowsapp.presentation.favorites.FavoritesViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun DisplayTVShows(
    searchViewModel: SearchViewModel,
    favoritesViewModel: FavoritesViewModel,
) {

    val state by searchViewModel.tvShowsState.collectAsState()

    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ){
        SearchComponentWrapper(
            query = query,
            onQueryChange = { query = it },
            onSearchClick = {
                // Handle search logic here
                searchViewModel.onClickSearchButton(query = query)
            }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Display two items in each row
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            when (state) {
                is SearchResponseState.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }
                is SearchResponseState.Empty -> {
                    item {
                        // Display UI for empty state
                    }
                }
                is SearchResponseState.Error -> {
                    item {
                        Text(text = "Error: ${(state as SearchResponseState.Error).errorMessage}")
                    }
                }
                is SearchResponseState.Success -> {
                    val successState = (state as SearchResponseState.Success)
                    items(successState.tvShows) { tvShow ->
                        // Display individual TV show item
                        val isFavorite = rememberSaveable{ mutableStateOf(tvShow.isFavorite)}

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                AsyncImage(
                                    model = tvShow.image.medium,
                                    contentDescription = tvShow.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                )

                                // Favorite icon
                                Icon(
                                    imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clickable {
                                            isFavorite.value = !isFavorite.value
                                            favoritesViewModel.toggleFavorite(tvShow)
                                        }
                                        .padding(8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }


}


