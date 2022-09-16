package com.example.crudproject

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

class UserRepository(val retro: RetroAPI) {

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