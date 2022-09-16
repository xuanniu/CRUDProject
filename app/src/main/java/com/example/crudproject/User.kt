package com.example.crudproject

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    val firstName: String,
    val lastName: String
)

data class Person(
    val firstName: String,
    val lastName: String,
    val occupation: String,
    val education: String,
    val phone: String,
    val about: String
) : Serializable