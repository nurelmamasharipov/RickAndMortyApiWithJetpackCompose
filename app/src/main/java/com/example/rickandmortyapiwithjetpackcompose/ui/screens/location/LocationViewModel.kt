package com.example.rickandmortyapiwithjetpackcompose.ui.screens.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapiwithjetpackcompose.data.dto.location.LocationResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationViewModel(
    private val repository: LocationRepository
) : ViewModel() {

    private val _locations = MutableStateFlow<List<LocationResponseDto.Location>>(emptyList())
    val locations: StateFlow<List<LocationResponseDto.Location>> get() = _locations

    fun fetchAllLocations() {
        viewModelScope.launch {
            try {
                val result = repository.fetchAllLocations()
                _locations.value = result
            } catch (e: Exception) {
                _locations.value = emptyList()
            }
        }
    }
}
