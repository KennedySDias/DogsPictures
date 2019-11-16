package com.kennedydias.data.mapper

import com.kennedydias.data.local.entity.BreedCache
import com.kennedydias.data.model.BreedModel
import com.kennedydias.data.remote.payload.BreedsPayload

class BreedMapper {

    fun mapCacheToModel(cache: BreedCache) = map(cache)

    private fun map(cache: BreedCache) = BreedModel(
        id = cache.id,
        name = cache.name,
        key = cache.key
    )

    fun mapModelToCache(model: BreedModel) = map(model)

    private fun map(model: BreedModel) = BreedCache(
        name = model.name,
        key = model.key
    )

    fun mapPayloadToModels(payload: BreedsPayload?) = map(payload)

    private fun map(payload: BreedsPayload?): List<BreedModel> {
        return if (payload != null) {
            val fields = payload.javaClass.kotlin.members

            val newList = mutableListOf<BreedModel>()
            fields.forEach {
                if (it != null) {
                    newList.add(
                        BreedModel(
                            name = it.name.toUpperCase(),
                            key = it.name
                        )
                    )
                }
            }

            newList
        } else {
            emptyList()
        }
    }

}