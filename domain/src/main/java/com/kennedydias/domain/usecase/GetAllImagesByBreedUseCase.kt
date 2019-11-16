package com.kennedydias.domain.usecase

import com.kennedydias.data.DogRepository

class GetAllImagesByBreedUseCase(
    private val dogRepository: DogRepository
) : UseCase<List<String>>() {

    lateinit var breed: String

    override suspend fun executeOnBackground(): List<String> {
        // No more business rules here
        return dogRepository.getImagesByBreed(breed)
    }

}