package com.kennedydias.data

import com.kennedydias.data.mapper.BreedMapper
import com.kennedydias.data.model.BreedModel
import com.kennedydias.data.remote.RetrofitBuilder
import com.kennedydias.data.remote.api.DogApi

interface BreedsRemoteDataSource {

    suspend fun getBreeds(): List<BreedModel>

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

}