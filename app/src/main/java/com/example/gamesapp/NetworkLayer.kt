package com.example.gamesapp

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.rawg.io/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()



    val gamesService: GamesService by lazy{
        retrofit.create(GamesService::class.java)
    }

    val apiClient = ApiClient(gamesService)
}