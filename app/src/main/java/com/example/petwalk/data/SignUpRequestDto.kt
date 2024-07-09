package com.example.petwalk.data

import com.google.gson.annotations.SerializedName

data class SignUpRequestDto(
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)