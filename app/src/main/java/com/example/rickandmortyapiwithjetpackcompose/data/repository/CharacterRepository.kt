package com.example.rickandmortyapiwithjetpackcompose.data.repository

import com.example.rickandmortyapiwithjetpackcompose.data.api.CharacterApiService
import com.example.rickandmortyapiwithjetpackcompose.data.dto.character.CharacterResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.dto.location.LocationResponseDto

class CharacterRepository(
    private val apiService: CharacterApiService
) {

    suspend fun fetchAllCharacters() : List<CharacterResponseDto.Character> {
        val response = apiService.fetchAllCharacters()
        return if (response.isSuccessful) {
            response.body()?.characterResults ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun fetchCharacterById(characterId: Int): CharacterResponseDto.Character {
        val response = apiService.fetchCharacterById(characterId)
        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Character not found")
        } else {
            throw Exception("Failed to load character")
        }
    }
}