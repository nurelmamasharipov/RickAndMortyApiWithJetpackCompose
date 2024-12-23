package com.example.rickandmortyapiwithjetpackcompose.data.repository

import com.example.rickandmortyapiwithjetpackcompose.data.api.LocationApiService
import com.example.rickandmortyapiwithjetpackcompose.data.dto.location.LocationResponseDto

class LocationRepository(
    private val locationApiService: LocationApiService
) {

    suspend fun fetchAllLocations(): List<LocationResponseDto.Location> {
        val response = locationApiService.fetchAllLocations()
        return if (response.isSuccessful) {
            response.body()?.locationResults ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun fetchLocationById(locationId: Int): LocationResponseDto.Location {
        val response = locationApiService.fetchLocationById(locationId)
        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Location not found")
        } else {
            throw Exception("Failed to load location")
        }
    }
}
