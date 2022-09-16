package com.example.crudproject.api

import com.example.crudproject.Person
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RetroAPI {
    @POST("users.json")
    suspend fun createUsers(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("users.json")
    suspend fun getAllUsers(): Response<Map<String, Person>>

    @PATCH("users/{id}.json")
    suspend fun updateUser(@Path("id") id: String, @Body requestBody: RequestBody): Response<ResponseBody>

    @DELETE("users/{id}.json")
    suspend fun deleteUser(@Path("id") id: String): Response<ResponseBody>
}