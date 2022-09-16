package com.example.crudproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }
}