package com.kennedydias.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class BreedCache(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val key: String
)