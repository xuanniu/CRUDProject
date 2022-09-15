package com.example.crudproject

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetroAPI {
    @POST("users.json")
    suspend fun createUsers(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("users.json")
    suspend fun getAllUsers(): Response<Map<String, User>>

    @PATCH("users/{id}.json")
    suspend fun updateUser(@Path("id") id: String, @Body requestBody: RequestBody): Response<ResponseBody>

    @DELETE("users/{id}.json")
    suspend fun deleteUser(@Path("id") id: String): Response<ResponseBody>

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