package com.kennedydias.data.remote.payload

import com.google.gson.annotations.SerializedName

data class BreedsResponsePayload(
    @SerializedName("message") val message: BreedsPayload,
    @SerializedName("status")
    val status: String
)