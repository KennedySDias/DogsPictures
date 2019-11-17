package com.kennedydias.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BreedData(
    val id: Int,
    val name: String,
    val key: String
) : Parcelable
