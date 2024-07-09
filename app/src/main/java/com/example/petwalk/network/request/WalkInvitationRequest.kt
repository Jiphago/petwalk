package com.example.petwalk.network.request


data class WalkInvitationRequest(
    val walkInvitaionCreateRequestDto: WalkInvitationCreateRequestDto,
    val uploadPhotos: List<String>
)