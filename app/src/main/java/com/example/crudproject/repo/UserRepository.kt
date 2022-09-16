package com.example.crudproject.repo

import com.example.crudproject.Person
import com.example.crudproject.api.RetroAPI
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(val retro: RetroAPI) {

    suspend fun createUser(user: Person): Response<ResponseBody>  {
        val json = Gson() .toJson(user)
        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
        return retro.createUsers(requestBody)
    }

    suspend fun getAllUsers(): Response<Map<String, Person>> {
        return retro.getAllUsers()
    }

    suspend fun deleteUser(id: String) {
        retro.deleteUser(id)
    }

    suspend fun updateUser(id: String, user: Person): Response<ResponseBody> {
        val json = Gson().toJson(user)
        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
        return retro.updateUser(id, requestBody)
    }
}