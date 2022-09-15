package com.example.crudproject

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(val repo: UserRepository) {

    var userList = MutableLiveData<Map<String,User>>()

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

    fun createUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.createUser(user)
            getAllUsers()
        }
    }
}