package com.example.rickandmortyapiwithjetpackcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapiwithjetpackcompose.data.api.EpisodesApiService
import com.example.rickandmortyapiwithjetpackcompose.data.dto.episodes.EpisodesResponseDto
import com.example.rickandmortyapiwithjetpackcompose.ui.screens.episode.paging.EpisodePagingSource
import kotlinx.coroutines.flow.Flow

class EpisodeRepository(
    private val apiService: EpisodesApiService
) {

    fun getEpisodesPager(): Flow<PagingData<EpisodesResponseDto.Episodes>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { EpisodePagingSource(apiService) }
        ).flow
    }

    suspend fun fetchEpisodeById(episodeId: Int): EpisodesResponseDto.Episodes {
        val response = apiService.fetchEpisodeById(episodeId)
        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Episode not found")
        } else {
            throw Exception("Failed to load episode")
        }
    }
}
