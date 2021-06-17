package com.example.gamesapp

import retrofit2.Response
import java.lang.Exception

class ApiClient(private val gamesService: GamesService) {
    suspend fun getGameById(gameId: Int): SimpleResponse<GamesResponse> {
        return safeApiCall { gamesService.getGameById(gameId) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }

}