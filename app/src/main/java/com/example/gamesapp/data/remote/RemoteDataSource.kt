package com.example.gamesapp.data.remote

import com.example.gamesapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource(){

    private val apiKey = apiKeyCons

    suspend fun getGame(gameId: Int) = getResult {
        apiService.getGameById(gameId,apiKey)
    }

    suspend fun getGames(
        search: String?,
        page: Int,
        pageSize: Int
    ) = apiService.getGames(search,page,pageSize,apiKey)
}