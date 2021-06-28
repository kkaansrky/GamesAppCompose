package com.example.gamesapp

import androidx.annotation.Nullable
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {
    //Kendisi çalışmadı null olmayan json objelerini null
    //olarak gösterdiği için onun yerine retrofit gson converter kullandım.


    /*val moshi = Moshi.Builder()
        .add(NullToEmptyStringAdapter())
        .addLast(KotlinJsonAdapterFactory()).build()*/


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.rawg.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()





    val gamesService: GamesService by lazy{
        retrofit.create(GamesService::class.java)
    }

    val apiClient = ApiClient(gamesService)
}

/*@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class NullToEmptyString

class NullToEmptyStringAdapter {
    @ToJson
    fun toJson(@NullToEmptyString value: String?): String? {
        return value
    }

    @FromJson
    @NullToEmptyString
    fun fromJson(@Nullable data: String?): String? {
        return data ?: ""
    }
}*/