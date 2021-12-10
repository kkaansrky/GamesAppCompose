package com.example.gamesapp.data.remote

import com.example.gamesapp.data.entity.GameResponse
import com.example.gamesapp.data.entity.games.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games/{game-id}")
    suspend fun getGameById(
        @Path("game-id") gameId: Int,
        @Query("key") apiKey: String
    ): Response<GameResponse>

    @GET("games")
    suspend fun getGames(
        @Query("search") name: String?,
        @Query("page") page: Int,
        @Query("page_size") page_size: Int,
        @Query("key") apiKey: String
    ): Response<GamesResponse>
}