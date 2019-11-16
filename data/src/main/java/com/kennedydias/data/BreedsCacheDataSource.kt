package com.kennedydias.data

import com.kennedydias.data.local.dao.BreedsDao
import com.kennedydias.data.mapper.BreedMapper
import com.kennedydias.data.model.BreedModel

interface BreedsCacheDataSource {

    suspend fun getAll(): List<BreedModel>

    suspend fun insert(item: BreedModel)

    suspend fun insertAll(item: List<BreedModel>)

    suspend fun update(item: List<BreedModel>)

    suspend fun delete(item: BreedModel)

    suspend fun deleteAll()

}

class BreedsCacheDataSourceImpl(
    private val breedsDao: BreedsDao,
    private val breedMapper: BreedMapper
) : BreedsCacheDataSource {

    override suspend fun getAll(): List<BreedModel> {
        return breedsDao.getAll().map { breedMapper.mapCacheToModel(it) }
    }

    override suspend fun insert(item: BreedModel) {
        breedsDao.insert(breedMapper.mapModelToCache(item))
    }

    override suspend fun insertAll(list: List<BreedModel>) {
        breedsDao.insertAll(list.map { breedMapper.mapModelToCache(it) })
    }

    override suspend fun update(list: List<BreedModel>) {
        breedsDao.update(list.map { breedMapper.mapModelToCache(it) })
    }

    override suspend fun delete(item: BreedModel) {
        breedsDao.delete(breedMapper.mapModelToCache(item))
    }

    override suspend fun deleteAll() {
        breedsDao.deleteAll()
    }

}