package com.example.crudproject

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface RetroAPI {
    @POST("users.json")
    suspend fun createUsers(@Body requestBody: RequestBody): Response<ResponseBody>

    companion object {
        var BASE_URL = "https://crudproject-3e32b-default-rtdb.firebaseio.com/"
        fun create() : RetroAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetroAPI::class.java)
        }
    }

}