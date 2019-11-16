package com.kennedydias.data

import com.kennedydias.data.mapper.BreedMapper
import com.kennedydias.data.model.BreedModel
import com.kennedydias.data.remote.RetrofitBuilder
import com.kennedydias.data.remote.api.DogApi

interface BreedsRemoteDataSource {

    suspend fun getBreeds(): List<BreedModel>

    suspend fun fetchImagesByBreed(breed: String): List<String>

}

class BreedsRemoteDataSourceImpl(
    private val retrofitBuilder: RetrofitBuilder,
    private val breedMapper: BreedMapper
) : BreedsRemoteDataSource {

    override suspend fun getBreeds(): List<BreedModel> {
        return breedMapper.mapPayloadToModels(
            retrofitBuilder
                .build(DogApi::class.java)
                .fetchBreeds()
                .message
        )
    }

    override suspend fun fetchImagesByBreed(breed: String): List<String> {
        return retrofitBuilder
            .build(DogApi::class.java)
            .fetchImagesByBreed(breed)
            .message ?: emptyList()
    }

}