package com.example.petwalk.data.repository

import com.example.petwalk.network.request.LoginRequest
import com.example.petwalk.network.response.LoadPostResponse
import com.example.petwalk.network.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class LoadPostRepository(
    private val apiService: ApiService
) {
    fun loadPost(
        loadPostRequest: LoginRequest,
        onResponse: (LoadPostResponse) -> Unit,
        onFailure: () -> Unit
    ){
        val call=apiService.loadPost()
        call.enqueue(object: Callback<LoadPostResponse>{
            override fun onResponse(
                call: Call<LoadPostResponse>,
                response: Response<LoadPostResponse>
            ) {


            }

            override fun onFailure(call: Call<LoadPostResponse>, t: Throwable) {


            }


        })

    }
}