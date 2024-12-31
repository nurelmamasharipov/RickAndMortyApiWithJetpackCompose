package com.example.rickandmortyapiwithjetpackcompose.data.repository

import CharacterPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapiwithjetpackcompose.data.api.CharacterApiService
import com.example.rickandmortyapiwithjetpackcompose.data.dto.character.CharacterResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.local.FavoriteCharacterEntity
import kotlinx.coroutines.flow.Flow

class CharacterRepository(
    private val apiService: CharacterApiService
) {

    fun getCharactersPager(): Flow<PagingData<FavoriteCharacterEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(apiService) }
        ).flow
    }

    suspend fun fetchCharacterById(characterId: Int): CharacterResponseDto.Character {
        try {
            val response = apiService.fetchCharacterById(characterId)

            return response.results.firstOrNull()
                ?: throw Exception("Character not found")
        } catch (e: Exception) {
            throw Exception("Failed to load character: ${e.message}")
        }
    }
}
