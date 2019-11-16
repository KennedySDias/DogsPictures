package com.kennedydias.domain.mapper

import com.kennedydias.data.model.BreedModel
import com.kennedydias.domain.model.BreedData

class BreedMapper {

    fun mapDataToModel(cache: BreedData) = map(cache)

    private fun map(cache: BreedData) = BreedModel(
        id = cache.id,
        name = cache.name,
        key = cache.key
    )

    fun mapModelToData(model: BreedModel) = map(model)

    private fun map(model: BreedModel) = BreedData(
        id = model.id ?: -1,
        name = model.name,
        key = model.key
    )

}