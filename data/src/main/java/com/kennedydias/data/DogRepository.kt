package com.kennedydias.data

import com.kennedydias.data.model.BreedModel

interface DogRepository {

    suspend fun getBreeds(forceUpdate: Boolean): List<BreedModel>

}

class DogRepositoryImpl(
    private val breedsCacheDataSource: BreedsCacheDataSource,
    private val breedsRemoteDataSource: BreedsRemoteDataSource
) : DogRepository {

    override suspend fun getBreeds(forceUpdate: Boolean): List<BreedModel> {
        return if (forceUpdate) {
            // Get data from Remote and save in Cache
            getBreedsFromRemote(true)
        } else {
            // Get data from Cache and return them, if the cache is empty, so get from Remote
            breedsCacheDataSource.getAll()
                .let { list ->
                    when {
                        list.isEmpty() -> getBreedsFromRemote(true)
                        else -> list
                    }
                }
        }
    }

    private suspend fun getBreedsFromRemote(forceUpdate: Boolean): List<BreedModel> {
        return breedsRemoteDataSource.getBreeds()
            .let { list ->
                if (forceUpdate) {
                    breedsCacheDataSource.update(list)
                }

                list
            }
    }

}