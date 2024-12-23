package com.example.rickandmortyapiwithjetpackcompose.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapiwithjetpackcompose.data.local.FavoriteCharacter
import com.example.rickandmortyapiwithjetpackcompose.data.local.FavoriteCharacterDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteCharacterDao: FavoriteCharacterDao) : ViewModel() {

    private val _favoriteCharacters = MutableStateFlow<List<FavoriteCharacter>>(emptyList())
    val favoriteCharacters: StateFlow<List<FavoriteCharacter>> = _favoriteCharacters

    init {
        getFavoriteCharacters()
    }

    private fun getFavoriteCharacters() {
        viewModelScope.launch {
            _favoriteCharacters.value = favoriteCharacterDao.getAllFavoriteCharacters()
        }
    }

    fun addCharacterToFavorites(character: FavoriteCharacter) {
        viewModelScope.launch {
            favoriteCharacterDao.addCharacterToFavorites(character)
            getFavoriteCharacters()
        }
    }

    fun removeCharacterFromFavorites(character: FavoriteCharacter) {
        viewModelScope.launch {
            favoriteCharacterDao.removeCharacterFromFavorites(character)
            getFavoriteCharacters()
        }
    }
}
