package com.kennedydias.data.remote.payload

import com.google.gson.annotations.SerializedName

// I'm settings all as nullable because I don't know what can be null or not
data class BreedsResponsePayload(
    @SerializedName("message") val message: BreedsPayload? = null,
    @SerializedName("status") val status: String? = null
)