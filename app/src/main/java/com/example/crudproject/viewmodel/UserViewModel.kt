package com.example.crudproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crudproject.Person
import com.example.crudproject.repo.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(val repo: UserRepository) : ViewModel() {

    var userList = MutableLiveData<Map<String, Person>>()

    init {
        getAllUsers()
    }

    fun getAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getAllUsers()
            if(res.isSuccessful) {
                userList.postValue(res.body())
            }
        }
    }

    fun getUser(id: String) : Person? {
        return userList.value?.getValue(id)
    }

    fun deleteUser(id: String) : Job {
        return CoroutineScope(Dispatchers.IO).launch {
            repo.deleteUser(id)
            getAllUsers()
        }
    }

    fun createUser(user: Person) : Job {
        return CoroutineScope(Dispatchers.IO).launch {
            repo.createUser(user)
            getAllUsers()
        }
    }

    fun updateUser(id: String, user: Person) : Job {
        return CoroutineScope(Dispatchers.IO).launch {
            repo.updateUser(id, user)
            getAllUsers()
        }
    }
}