package com.example.crudproject

import java.io.Serializable

data class Person(
    val firstName: String,
    val lastName: String,
    val occupation: String,
    val education: String,
    val phone: String,
    val about: String
) : Serializable