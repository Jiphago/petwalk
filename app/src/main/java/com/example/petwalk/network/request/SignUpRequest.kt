package com.example.petwalk.network.request

import com.example.petwalk.data.SignUpRequestDto
import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("signUpRequestDto")
    val signUpRequestDto: SignUpRequestDto,
    @SerializedName("uploadPhoto")
    val uploadPhoto: String
)
