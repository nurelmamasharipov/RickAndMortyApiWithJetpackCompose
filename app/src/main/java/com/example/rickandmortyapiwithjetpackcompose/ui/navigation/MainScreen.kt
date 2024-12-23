package com.example.rickandmortyapiwithjetpackcompose.ui.navigation

import CharacterScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.CharactersViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.favorite.FavoriteViewModel
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.detail.CharacterDetailScreen
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.episode.EpisodeScreen
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.episode.detail.EpisodeDetailScreen
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.favorite.FavoriteScreen
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.location.LocationScreen
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.location.detail.LocationDetailScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val favoriteViewModel: FavoriteViewModel = viewModel()
    val charactersViewModel: CharactersViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Characters.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Characters.route) {
                CharacterScreen(navController, charactersViewModel, favoriteViewModel)
            }
            composable(BottomNavItem.Locations.route) {
                LocationScreen(navController)
            }
            composable(BottomNavItem.Episodes.route) {
                EpisodeScreen(navController)
            }
            composable(BottomNavItem.Favorite.route) {
                FavoriteScreen(viewModel = favoriteViewModel)
            }
            composable("character_detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                id?.let { CharacterDetailScreen(it) }
            }
            composable("location_detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                id?.let { LocationDetailScreen(it) }
            }
            composable("episode_detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                id?.let { EpisodeDetailScreen(it) }
            }
        }
    }
}