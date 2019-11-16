package com.kennedydias.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kennedydias.data.local.BreedsDataBase
import com.kennedydias.data.local.dao.BreedsDao
import com.kennedydias.data.local.entity.BreedCache
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BreedsDBReadWriteTest {

    private lateinit var breedsDao: BreedsDao
    private lateinit var db: BreedsDataBase

    private val breedCacheFull = BreedCache(
        id = 1,
        name = "Affenpinscher",
        key = "affenpinscher"
    )

    private val breedCacheFull2 = BreedCache(
        id = 2,
        name = "African",
        key = "african"
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BreedsDataBase::class.java
        ).build()
        breedsDao = db.breedsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeBreedAndReadInList() {
        runBlocking { breedsDao.insert(breedCacheFull) }
        runBlocking { breedsDao.insert(breedCacheFull2) }

        val list = runBlocking { breedsDao.getAll() }
        assertEquals(listOf(breedCacheFull, breedCacheFull2), list)
    }

    @Test
    @Throws(Exception::class)
    fun writeBreedAndDelete() {
        runBlocking { breedsDao.insert(breedCacheFull) }
        runBlocking { breedsDao.delete(breedCacheFull) }

        val list = runBlocking { breedsDao.getAll() }
        assertEquals(listOf<BreedCache>(), list)
    }

    @Test
    @Throws(Exception::class)
    fun writeBreedAndDeleteAll() {
        runBlocking { breedsDao.insert(breedCacheFull) }
        runBlocking { breedsDao.deleteAll() }

        val list = runBlocking { breedsDao.getAll() }
        assertEquals(listOf<BreedCache>(), list)
    }

    @Test
    @Throws(Exception::class)
    fun writeBreedAndUpdate() {
        runBlocking { breedsDao.insert(breedCacheFull) }
        runBlocking { breedsDao.insert(breedCacheFull2) }
        runBlocking { breedsDao.update(listOf(breedCacheFull)) }

        val list = runBlocking { breedsDao.getAll() }
        assertEquals(listOf(breedCacheFull), list)
    }

}