package com.example.gamesapp

import retrofit2.Response
import java.lang.Exception

class ApiClient(private val gamesService: GamesService) {


    suspend fun getGameById(gameId: Int, apiKey: String): SimpleResponse<GameResponse> {
        return safeApiCall { gamesService.getGameById(gameId, apiKey) }
    }

    suspend fun getGames(
        search: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): SimpleResponse<GamesResponse> {
        return safeApiCall { gamesService.getGames(search, page, pageSize, apiKey) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }

}