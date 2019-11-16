package com.kennedydias.data.remote.api

import com.kennedydias.data.remote.payload.BreedsResponsePayload
import retrofit2.http.GET


interface DogApi {

    @GET("/api/breeds/list/all")
    suspend fun fetchBreeds(): BreedsResponsePayload

}