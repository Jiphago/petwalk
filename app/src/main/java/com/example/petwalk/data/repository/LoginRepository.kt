package com.example.petwalk.data.repository

import android.util.Log
import com.example.petwalk.network.request.LoginRequest
import com.example.petwalk.network.response.LoginResponse
import com.example.petwalk.network.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(
    private val apiService: ApiService
) {
    fun login(
        loginRequest: LoginRequest,
        onResponse: (LoginResponse) -> Unit,
        onFailure: () -> Unit
    ) {
        val call = apiService.login(loginRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("LoginRepository", "Response: ${response.raw()}")
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResponse(it)
                    } ?: run {
                        Log.e("LoginRepository", "Response body is null")
                        onFailure()
                    }
                } else {
                    Log.e("LoginRepository", "Login failed: ${response.code()} ${response.errorBody()?.string()}")
                    onFailure()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LoginRepository", "Request failed", t)
                onFailure()
            }
        })
    }
}
