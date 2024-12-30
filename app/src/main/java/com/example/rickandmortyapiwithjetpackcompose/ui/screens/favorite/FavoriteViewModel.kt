package com.example.rickandmortyapiwithjetpackcompose.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapiwithjetpackcompose.data.local.FavoriteCharacterEntity
import com.example.rickandmortyapiwithjetpackcompose.data.local.FavoriteCharacterDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteCharacterDao: FavoriteCharacterDao) : ViewModel() {

    private val _favoriteCharactersEntity = MutableStateFlow<List<FavoriteCharacterEntity>>(emptyList())
    val favoriteCharactersEntity: StateFlow<List<FavoriteCharacterEntity>> = _favoriteCharactersEntity

    init {
        getFavoriteCharacters()
    }

    private fun getFavoriteCharacters() {
        viewModelScope.launch {
            _favoriteCharactersEntity.value = favoriteCharacterDao.getAllFavoriteCharacters()
        }
    }

    fun addCharacterToFavorites(character: FavoriteCharacterEntity) {
        viewModelScope.launch {
            favoriteCharacterDao.addCharacterToFavorites(character)
            getFavoriteCharacters()
        }
    }

    fun removeCharacterFromFavorites(character: FavoriteCharacterEntity) {
        viewModelScope.launch {
            favoriteCharacterDao.removeCharacterFromFavorites(character)
            getFavoriteCharacters()
        }
    }
}