package com.example.rickandmortyapiwithjetpackcompose.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.CharacterScreen
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.detail.CharacterDetailScreen
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.location.LocationScreen
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.location.detail.LocationDetailScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Characters.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Characters.route) {
                CharacterScreen(navController)
            }
            composable(BottomNavItem.Locations.route) {
                LocationScreen(navController)
            }
            composable("character_detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                id?.let { CharacterDetailScreen(it) }
            }
            composable("location_detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                id?.let { LocationDetailScreen(it) }
            }
        }
    }
}