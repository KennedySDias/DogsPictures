package com.kennedydias.data.local.dao

import androidx.room.*
import com.kennedydias.data.local.entity.BreedCache

@Dao
interface BreedsDao {

    @Query(value = "SELECT * FROM breeds")
    suspend fun getAll(): List<BreedCache>

    @Insert
    suspend fun insert(breed: BreedCache)

    @Insert
    suspend fun insertAll(breeds: List<BreedCache>)

    @Transaction
    suspend fun update(breeds: List<BreedCache>) {
        deleteAll()
        insertAll(breeds)
    }

    @Delete
    suspend fun delete(breed: BreedCache)

    @Query("DELETE FROM breeds")
    suspend fun deleteAll()

}