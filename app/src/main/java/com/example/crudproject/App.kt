package com.example.crudproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    //all singletons should be here
    override fun onCreate() {
        super.onCreate()
    }
}