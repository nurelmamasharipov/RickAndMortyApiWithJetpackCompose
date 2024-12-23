package com.example.rickandmortyapiwithjetpackcompose.ui.screens.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortyapiwithjetpackcompose.data.dto.location.LocationResponseDto
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationScreen(
    navController: NavHostController,
    viewModel: LocationViewModel = koinViewModel()
) {

    val locations = viewModel.locations.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchAllLocations()
    }

    LazyColumn {
        items(locations) { location ->
            LocationItem(location = location) {
                navController.navigate("location_detail/${location.id}")
            }
        }
    }
}

@Composable
fun LocationItem(location: LocationResponseDto.Location, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val painter = rememberAsyncImagePainter(location.imageUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = location.name, style = MaterialTheme.typography.titleLarge)
            Text(text = location.type, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
