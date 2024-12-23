package com.example.rickandmortyapiwithjetpackcompose.ui.screens.episode

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapiwithjetpackcompose.data.dto.episodes.EpisodesResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.repository.EpisodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodesViewModel(
    private val repository: EpisodeRepository
) : ViewModel(){
    private val _episodes = mutableStateOf<List<EpisodesResponseDto.Episodes>>(emptyList())
    val episodes: State<List<EpisodesResponseDto.Episodes>> = _episodes

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun fetchAllEpisodes() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = withContext(Dispatchers.IO) {
                    repository.fetchAllEpisodes()
                }
                _episodes.value = result
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error loading characters: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}