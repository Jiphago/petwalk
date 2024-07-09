package com.example.petwalk.data.repository

import android.util.Log
import com.example.petwalk.network.response.ApiResponse
import com.example.petwalk.network.request.WalkInvitationCreateRequestDto
import com.example.petwalk.network.request.WalkInvitationRequest
import com.example.petwalk.network.response.WalkInvitationDetailsResponse
import com.example.petwalk.network.response.WalkInvitationResponse
import com.example.petwalk.network.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WalkInvitationRepository(
    private val apiService: ApiService
) {
    fun createWalkInvitation(
        walkInvitationDetailsResponse: WalkInvitationDetailsResponse,
        onResponse: (ApiResponse) -> Unit,
        onFailure: () -> Unit
    ) {
        val request = WalkInvitationRequest(
            WalkInvitationCreateRequestDto(
                title = walkInvitationDetailsResponse.title,
                content = walkInvitationDetailsResponse.content,
                latitude = walkInvitationDetailsResponse.latitude,
                longitude = walkInvitationDetailsResponse.longitude,
                detailedLocation = walkInvitationDetailsResponse.detailedLocation,
                walkDateTime = walkInvitationDetailsResponse.walkDateTime
            ),
            uploadPhotos = walkInvitationDetailsResponse.uploadPhotos
        )

        val call = apiService.createWalkInvitation("Bearer ${walkInvitationDetailsResponse.accessToken}", request)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResponse(it)
                        return
                    }
                }
                onFailure()
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("ApiManager", "Request failed", t)
                onFailure()
            }
        })
    }

    fun getWalkInvitations(
        accessToken: String,
        onResponse: (List<WalkInvitationResponse>) -> Unit,
        onFailure: () -> Unit
    ) {
        val call = apiService.getWalkInvitations("Bearer $accessToken")
        call.enqueue(object : Callback<List<WalkInvitationResponse>> {
            override fun onResponse(call: Call<List<WalkInvitationResponse>>, response: Response<List<WalkInvitationResponse>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResponse(it)
                        return
                    }
                }
                onFailure()
            }

            override fun onFailure(call: Call<List<WalkInvitationResponse>>, t: Throwable) {
                Log.e("ApiManager", "Request failed", t)
                onFailure()
            }
        })
    }
}

