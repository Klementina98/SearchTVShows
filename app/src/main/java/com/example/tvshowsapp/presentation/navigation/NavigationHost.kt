package com.example.tvshowsapp.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tvshowsapp.presentation.favorites.FavoritesScreen
import com.example.tvshowsapp.presentation.search.DisplayTVShows
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tvshowsapp.presentation.favorites.FavoritesViewModel
import com.example.tvshowsapp.presentation.search.SearchViewModel

@Composable
fun NavigationHost(
    searchViewModel: SearchViewModel,
    favoritesViewModel: FavoritesViewModel,
    ) {
    val navController = rememberNavController()

    Box {
        NavHost(navController = navController, startDestination = Screen.Search.route) {
            composable(route = Screen.Search.route) {
                DisplayTVShows(searchViewModel = searchViewModel, favoritesViewModel = favoritesViewModel)
            }
            composable(route = Screen.Favorites.route) {
                FavoritesScreen(favoritesViewModel = favoritesViewModel)
            }
        }

        BottomNavigation(
            modifier = Modifier.align(Alignment.BottomCenter),
            backgroundColor = MaterialTheme.colorScheme.surface
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            BottomNavigationItem(
                icon = { Icon(Icons.Default.Search, contentDescription = null) },
                label = { Text("Search") },
                selected = currentRoute == Screen.Search.route,
                onClick = {
                    navController.navigate(Screen.Search.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                label = { Text("Favorites") },
                selected = currentRoute == Screen.Favorites.route,
                onClick = {
                    navController.navigate(Screen.Favorites.route) {
                    }
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Search : Screen("search")
    object Favorites : Screen("favorites")
}
