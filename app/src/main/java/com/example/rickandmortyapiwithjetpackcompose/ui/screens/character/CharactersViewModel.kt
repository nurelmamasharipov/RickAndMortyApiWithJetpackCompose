package com.example.rickandmortyapiwithjetpackcompose.ui.screens.character

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapiwithjetpackcompose.data.dto.character.CharacterResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.local.FavoriteCharacterEntity
import com.example.rickandmortyapiwithjetpackcompose.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    val charactersPager: Flow<PagingData<FavoriteCharacterEntity>> =
        repository.getCharactersPager().cachedIn(viewModelScope)
}
