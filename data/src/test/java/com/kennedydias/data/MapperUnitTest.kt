package com.kennedydias.data

import com.kennedydias.data.di.mapperDataModule
import com.kennedydias.data.local.entity.BreedCache
import com.kennedydias.data.mapper.BreedMapper
import com.kennedydias.data.model.BreedModel
import com.kennedydias.data.remote.payload.BreedsPayload
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class MapperUnitTest : AutoCloseKoinTest() {

    private val modules = listOf(
        mapperDataModule
    )

    private val breedMapper by inject<BreedMapper>()

    private val breedCacheEmpty = BreedCache(
        id = 0,
        name = "",
        key = ""
    )

    private val breedModelEmpty = BreedModel(
        id = 0,
        name = "",
        key = ""
    )

    private val breedPayloadEmpty = BreedsPayload(
        affenpinscher = emptyList(),
        african = emptyList(),
        airedale = emptyList(),
        akita = emptyList()
    )

    private val breedPayloadNull = BreedsPayload(
        affenpinscher = null,
        african = null,
        airedale = null,
        akita = null
    )

    private val breedModelsEmptyFromPayload = listOf(
        BreedModel(
            id = null,
            name = "Affenpinscher",
            key = "affenpinscher"
        ),
        BreedModel(
            id = null,
            name = "African",
            key = "african"
        ),
        BreedModel(
            id = null,
            name = "Airedale",
            key = "airedale"
        ),
        BreedModel(
            id = null,
            name = "Akita",
            key = "akita"
        )
    )

    @Before
    fun before() {
        startKoin {
            modules(modules)
        }
    }

    @Test
    fun `BreedMapper Cache To Model Empty`() {
        val cache = breedCacheEmpty
        val model = breedModelEmpty

        val result = breedMapper.mapCacheToModel(cache)

        assertEquals(model, result)
    }

    @Test
    fun `BreedMapper Model To Cache Empty`() {
        val cache = breedCacheEmpty
        val model = breedModelEmpty

        val result = breedMapper.mapCacheToModel(cache)

        assertEquals(model, result)
    }

    @Test
    fun `BreedMapper Payload To Models Empty`() {
        val payload = breedPayloadEmpty
        val model = breedModelsEmptyFromPayload

        val result = breedMapper.mapPayloadToModels(payload)

        assertEquals(model, result)
    }

    @Test
    fun `BreedMapper Payload To Models Null`() {
        val payload = null
        val result = breedMapper.mapPayloadToModels(payload)

        assertEquals(emptyList<BreedModel>(), result)
    }

    @Test
    fun `BreedMapper Payload To Models Null2`() {
        val payload = breedPayloadNull
        val result = breedMapper.mapPayloadToModels(payload)

        assertEquals(emptyList<BreedModel>(), result)
    }


}
