package com.kennedydias.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class BreedCache(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val key: String
)