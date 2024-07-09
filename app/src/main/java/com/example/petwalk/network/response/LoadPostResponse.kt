package com.example.petwalk.network.response

data class LoadPostResponse(
    val title:String,
    val latitude: Int,
    val longitude: Int,
    val detailedLocation: String,
    val walkDateTime: String,
    val walkingStatus: String,
    val walkInvitationPhotoUrl: String
)

/*
* [
  {
    "title": "string",
    "latitude": 0,
    "longitude": 0,
    "detailedLocation": "string",
    "walkDateTime": "2024-05-30T11:35:50.615Z",
    "walkingStatus": "string",
    "walkInvitationPhotoUrl": "string"
  }
]
* */