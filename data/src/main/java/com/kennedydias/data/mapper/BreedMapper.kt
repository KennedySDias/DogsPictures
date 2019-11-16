package com.kennedydias.data.mapper

import com.google.gson.Gson
import com.google.gson.JsonParser
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
            // Transform all attributes from an object to a list of objects
            val parser = JsonParser()
            val element = parser.parse(Gson().toJson(payload))
            val obj = element.asJsonObject

            val entries = obj.entrySet()

            val newList = mutableListOf<BreedModel>()
            for ((key) in entries) {
                if (key != null) {
                    newList.add(
                        BreedModel(
                            name = key.capitalize(),
                            key = key
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