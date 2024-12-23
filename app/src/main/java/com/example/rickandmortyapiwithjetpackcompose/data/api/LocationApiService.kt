package com.example.rickandmortyapiwithjetpackcompose.data.api

import com.example.rickandmortyapiwithjetpackcompose.data.dto.location.LocationResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApiService {

    @GET("location")
    suspend fun fetchAllLocations(): Response<LocationResponseDto>

    @GET("location/{id}")
    suspend fun fetchLocationById(@Path("id") locationId: Int): Response<LocationResponseDto.Location>
}