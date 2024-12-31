package com.example.rickandmortyapiwithjetpackcompose.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Characters : BottomNavItem("characters", Icons.Default.Person, "Characters")
    object Locations : BottomNavItem("locations", Icons.Default.LocationOn, "Locations")
    object Episodes : BottomNavItem("episodes", Icons.Default.Face, "Episodes")
    object Favorite : BottomNavItem("favorite", Icons.Default.Favorite, "Favorites")
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Characters,
        BottomNavItem.Locations,
        BottomNavItem.Episodes,
        BottomNavItem.Favorite
    )
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        items.forEach { item ->
            val isSelected = currentRoute == item.route


            val iconSize by animateFloatAsState(targetValue = if (isSelected) 32f else 24f)


            val iconColor by animateColorAsState(targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray)

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(iconSize.dp),
                        tint = iconColor
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        if (currentRoute != item.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = false }
                        }
                    }
                }
            )
        }
    }
}