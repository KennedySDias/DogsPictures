package com.kennedydias.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kennedydias.data.local.dao.BreedsDao
import com.kennedydias.data.local.entity.BreedCache

@Database(entities = [BreedCache::class], version = 1)
abstract class BreedsDataBase : RoomDatabase() {
    abstract fun breedsDao(): BreedsDao

    companion object {
        fun createDataBase(context: Context): BreedsDao {
            return Room
                .databaseBuilder(context, BreedsDataBase::class.java, "Breeds.db")
                .build()
                .breedsDao()
        }
    }
}