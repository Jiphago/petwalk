package com.example.petwalk.data.repository

import android.util.Log
import com.example.petwalk.data.SignUpRequestDto
import com.example.petwalk.network.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpRepository(
    private val apiService: ApiService
) {
    fun signUp(
        signUpRequestDto: SignUpRequestDto,
        uploadPhoto: MultipartBody.Part,
        onResponse: (String) -> Unit,
        onFailure: () -> Unit
    ) {
        val email = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequestDto.email)
        val nickName = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequestDto.nickName)
        val password = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequestDto.password)
        val latitude = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequestDto.latitude.toString())
        val longitude = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequestDto.longitude.toString())

        val call = apiService.signUp(email, nickName, password, latitude, longitude, uploadPhoto)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("SignUpRepository", "Response: ${response.raw()}")
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResponse(it)
                    } ?: run {
                        Log.e("SignUpRepository", "Response body is null")
                        onFailure()
                    }
                } else {
                    Log.e("SignUpRepository", "Sign up failed: ${response.code()} ${response.errorBody()?.string()}")
                    onFailure()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("SignUpRepository", "Request failed", t)
                onFailure()
            }
        })
    }
}
