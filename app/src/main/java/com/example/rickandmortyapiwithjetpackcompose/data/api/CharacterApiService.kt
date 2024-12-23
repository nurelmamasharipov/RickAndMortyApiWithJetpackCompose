package com.example.rickandmortyapiwithjetpackcompose.data.api

import com.example.rickandmortyapiwithjetpackcompose.data.dto.character.CharacterResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.dto.location.LocationResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {

    @GET("character")
    suspend fun fetchAllCharacters(): Response<CharacterResponseDto>

    @GET("character/{id}")
    suspend fun fetchCharacterById(@Path("id") characterId: Int): Response<CharacterResponseDto.Character>
}