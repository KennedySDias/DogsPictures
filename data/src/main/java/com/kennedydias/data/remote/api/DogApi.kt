package com.kennedydias.data.remote.api

import com.kennedydias.data.remote.payload.BreedsResponsePayload
import com.kennedydias.data.remote.payload.ImagesResponsePayload
import retrofit2.http.GET
import retrofit2.http.Path


interface DogApi {

    @GET("/api/breeds/list/all")
    suspend fun fetchBreeds(): BreedsResponsePayload

    @GET("/api/breed/{breed}/images")
    suspend fun fetchImagesByBreed(@Path("breed") breed: String): ImagesResponsePayload

}