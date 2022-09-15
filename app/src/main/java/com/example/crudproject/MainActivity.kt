package com.example.crudproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retro = RetroAPI.create()
        val repo = UserRepository(retro)
        repo.getAllUsers()
    }
}