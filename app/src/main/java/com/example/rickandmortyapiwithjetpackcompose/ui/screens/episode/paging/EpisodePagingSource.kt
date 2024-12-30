package com.example.rickandmortyapiwithjetpackcompose.ui.screens.episode.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapiwithjetpackcompose.data.api.EpisodesApiService
import com.example.rickandmortyapiwithjetpackcompose.data.dto.episodes.EpisodesResponseDto

class EpisodePagingSource(private val apiService: EpisodesApiService) : PagingSource<Int, EpisodesResponseDto.Episodes>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodesResponseDto.Episodes> {
        val position = params.key ?: 1

        return try {
            val response = apiService.fetchAllEpisodes(position)
            val episodes = response.episodesResults

            LoadResult.Page(
                data = episodes,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (episodes.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EpisodesResponseDto.Episodes>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }
}
