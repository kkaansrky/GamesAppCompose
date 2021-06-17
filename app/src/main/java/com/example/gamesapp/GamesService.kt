package com.example.gamesapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GamesService {

    @GET("games/{game-id}?key=YOURKEY")
    fun getGameById(
        @Path("game-id") gameId: Int
    ): Response<GamesResponse>
}