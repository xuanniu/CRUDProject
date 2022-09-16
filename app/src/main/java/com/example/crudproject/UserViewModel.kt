package com.example.crudproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val repo: UserRepository
    val retro: RetroAPI

    init{
        retro = RetroAPI.create()
        repo = UserRepository(retro)
    }

    var userList = MutableLiveData<Map<String,Person>>()

    fun getAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getAllUsers()
            if(res.isSuccessful) {
                userList.postValue(res.body())
            }
        }
    }

    fun deleteUser(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteUser(id)
            getAllUsers()
        }
    }

    fun createUser(user: Person) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.createUser(user)
            getAllUsers()
        }
    }

    fun updateUser(id: String, user: Person){
        CoroutineScope(Dispatchers.IO).launch {
            repo.updateUser(id, user)
            getAllUsers()
        }
    }
}