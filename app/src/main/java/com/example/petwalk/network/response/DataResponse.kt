package com.example.petwalk.network.response

import com.example.petwalk.data.TokenData

data class DataResponse(
    val hasBaby: Boolean,
    val token: TokenData
)