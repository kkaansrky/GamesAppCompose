package com.example.gamesapp.data.remote

import com.example.gamesapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource(){

    private val apiKey = "5974310c00fa46578244fa46726820fa"

    suspend fun getGame(gameId: Int) = getResult {
        apiService.getGameById(gameId,apiKey)
    }

    suspend fun getGames(
        search: String?,
        page: Int,
        pageSize: Int
    ) = apiService.getGames(search,page,pageSize,apiKey)
}