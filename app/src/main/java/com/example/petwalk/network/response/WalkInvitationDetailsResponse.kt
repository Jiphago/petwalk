package com.example.petwalk.network.response

data class WalkInvitationDetailsResponse(
    val accessToken: String,
    val title: String,
    val content: String,
    val latitude: Double,
    val longitude: Double,
    val detailedLocation: String,
    val walkDateTime: String,
    val uploadPhotos: List<String>
)