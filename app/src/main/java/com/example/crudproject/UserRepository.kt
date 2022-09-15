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

    fun createUser()  {
        val json = Gson() .toJson(User("Dan","Smith"))
        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
         CoroutineScope(Dispatchers.IO).launch {
             println(retro.createUsers(requestBody).errorBody()?.string())
        }
    }
}