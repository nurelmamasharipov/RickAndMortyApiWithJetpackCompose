package com.example.rickandmortyapiwithjetpackcompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteCharacterDao {

    @Insert
    suspend fun addCharacterToFavorites(character: FavoriteCharacter)

    @Delete
    suspend fun removeCharacterFromFavorites(character: FavoriteCharacter)

    @Query("SELECT * FROM favorite_characters")
    suspend fun getAllFavoriteCharacters(): List<FavoriteCharacter>
}