package com.example.crudproject.di

import com.example.crudproject.api.RetroAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun getRetroApi(): RetroAPI {
        var BASE_URL = "https://crudproject-3e32b-default-rtdb.firebaseio.com/"
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetroAPI::class.java)
    }
}