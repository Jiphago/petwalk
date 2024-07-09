package com.example.petwalk.network.request

data class WalkInvitationCreateRequestDto(
    val title: String,
    val content: String,
    val latitude: Double,
    val longitude: Double,
    val detailedLocation: String,
    val walkDateTime: String
)
