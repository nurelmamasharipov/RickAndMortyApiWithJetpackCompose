package com.example.rickandmortyapiwithjetpackcompose.data.dto.character

data class CharacterResponseDto(
    val characterResults: List<Character>
) {
    data class Character(
        val id: Int,
        val name: String,
        val status: String,
        val species: String,
        val image: String
    )
}
