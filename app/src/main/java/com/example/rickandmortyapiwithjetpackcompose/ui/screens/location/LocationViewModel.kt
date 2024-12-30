package com.example.rickandmortyapiwithjetpackcompose.ui.screens.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapiwithjetpackcompose.data.dto.location.LocationResponseDto
import com.example.rickandmortyapiwithjetpackcompose.data.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class LocationViewModel(
    private val repository: LocationRepository
)  : ViewModel() {

    val locationPager : Flow<PagingData<LocationResponseDto.Location>> =
        repository.getLocationsPager().cachedIn(viewModelScope)
}