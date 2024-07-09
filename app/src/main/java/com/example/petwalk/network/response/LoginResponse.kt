package com.example.petwalk.network.response

data class LoginResponse(
    val authorization: String,
    val authorizationRefresh: String
)