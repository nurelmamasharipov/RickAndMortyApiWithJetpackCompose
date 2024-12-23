package com.example.rickandmortyapiwithjetpackcompose.data.repository

import com.example.rickandmortyapiwithjetpackcompose.data.api.EpisodesApiService
import com.example.rickandmortyapiwithjetpackcompose.data.dto.episodes.EpisodesResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.dto.location.LocationResponseDto

class EpisodeRepository(
    private val apiService: EpisodesApiService
) {
    suspend fun fetchAllEpisodes(): List<EpisodesResponseDto.Episodes> {
        val response = apiService.fetchAllEpisodes()
        return if (response.isSuccessful) {
            response.body()?.episodesResults ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun fetchEpisodeById(episodeId: Int): EpisodesResponseDto.Episodes {
        val response = apiService.fetchEpisodeById(episodeId)
        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Location not found")
        } else {
            throw Exception("Failed to load location")
        }
    }
}