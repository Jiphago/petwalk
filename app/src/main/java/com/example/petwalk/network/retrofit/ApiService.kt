package com.example.petwalk.network.retrofit

import com.example.petwalk.network.request.LoginRequest
import com.example.petwalk.network.request.SignUpRequest
import com.example.petwalk.network.response.ApiResponse
import com.example.petwalk.network.request.WalkInvitationRequest
import com.example.petwalk.network.response.LoadPostResponse
import com.example.petwalk.network.response.LoginResponse
import com.example.petwalk.network.response.WalkInvitationResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("/api/home")
    fun createWalkInvitation(
        @Header("Authorization") accessToken: String,
        @Body request: WalkInvitationRequest
    ): Call<ApiResponse>

    @GET("/api/home/me")
    fun getWalkInvitations(
        @Header("Authorization") accessToken: String
    ): Call<List<WalkInvitationResponse>>

    @Multipart
    @POST("/api/members/sign-up")
    fun signUp(
        @Part("email") email: RequestBody,
        @Part("nickName") nickName: RequestBody,
        @Part("password") password: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part uploadPhoto: MultipartBody.Part
    ): Call<String>

    @Headers("Content-Type:application/json")
    @POST("/api/members/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @Headers("Content-Type:application/json")
    @POST("/api/home/me")
    fun loadPost(void: Void): Call<LoadPostResponse>
    abstract fun loadPost(): Call<LoadPostResponse>

}