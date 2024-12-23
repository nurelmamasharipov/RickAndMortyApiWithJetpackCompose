package com.example.rickandmortyapiwithjetpackcompose.ui.screens.episode.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortyapiwithjetpackcompose.data.dto.character.CharacterResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.dto.episodes.EpisodesResponseDto
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.character.detail.CharacterDetailViewModel

@Composable
fun EpisodeDetailScreen(episodeId : Int?) {

    val viewModel: EpisodeDetailViewModel = viewModel()

    val episode = remember { mutableStateOf<EpisodesResponseDto.Episodes?>(null) }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(episodeId) {
        episodeId?.let {
            viewModel.fetchEpisodeById(it) { fetchedEpisode ->
                episode.value = fetchedEpisode
                isLoading.value = false
            }
        }
    }
}