package com.kennedydias.domain.usecase

import com.kennedydias.data.DogRepository
import com.kennedydias.domain.mapper.BreedMapper
import com.kennedydias.domain.model.BreedData

class GetAllBreedsUseCase(
    private val dogRepository: DogRepository,
    private val breedMapper: BreedMapper
) : UseCase<List<BreedData>>() {

    var forceUpdate: Boolean = true

    override suspend fun executeOnBackground(): List<BreedData> {
        // No more business rules here
        return dogRepository.getBreeds(forceUpdate).map { breedMapper.mapModelToData(it) }
    }

}