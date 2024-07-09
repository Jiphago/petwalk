package com.example.petwalk.network.response

data class WalkInvitationResponse(
    val title: String,
    val latitude: Double,
    val longitude: Double,
    val detailedLocation: String,
    val walkDateTime: String,
    val walkingStatus: String,
    val walkInvitationPhotoUrl: String
)