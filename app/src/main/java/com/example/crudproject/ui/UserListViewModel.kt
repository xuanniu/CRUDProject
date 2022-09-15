package com.example.crudproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crudproject.Person
import com.example.crudproject.User

class UserListViewModel : ViewModel() {

    var data = MutableLiveData<List<Person>>()

    fun fetchUsers(){
        //mock data - replace with retrofit call
        data.postValue(listOf(
            Person("Saul", "Goodman", "Lawyer", "University of American Samoa", "555-5555", "\"Criminal\" lawyer"),
            Person("Hector", "Salamanca", "Cartel Enforcer", "None", "555-5555", "DING DING DING"),
            Person("Gustavo", "Fring", "Businessman", "University of Chile", "555-5555", "Owner of Los Pollos Hermanos"),
            Person("Hank", "Schrader", "DEA agent", "University of New Mexico", "555-5555", "Collects minerals"),
            Person("Ignacio", "Varga", "Cartel member", "None", "555-5555", "Made Hector DING DING DING"),
        ))
    }
}