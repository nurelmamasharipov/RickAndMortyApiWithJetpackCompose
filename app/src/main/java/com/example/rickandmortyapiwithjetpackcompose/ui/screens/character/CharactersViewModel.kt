package com.example.rickandmortyapiwithjetpackcompose.ui.screens.character

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapiwithjetpackcompose.data.dto.character.CharacterResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _characters = mutableStateOf<List<CharacterResponseDto.Character>>(emptyList())
    val characters: State<List<CharacterResponseDto.Character>> = _characters

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun fetchAllCharacters() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = withContext(Dispatchers.IO) {
                    repository.fetchAllCharacters()
                }
                _characters.value = result
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error loading characters: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

