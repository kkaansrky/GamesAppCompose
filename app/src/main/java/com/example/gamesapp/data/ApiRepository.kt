package com.example.gamesapp.data

import com.example.gamesapp.data.remote.RemoteDataSource
import com.example.gamesapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource
) {
    fun getGame(gameId: Int) = performNetworkOperation {
        remoteDataSource.getGame(gameId)
    }

    suspend fun getGames(
        search: String,
        page: Int,
        pageSize: Int
    ) = remoteDataSource.getGames(search, page, pageSize)
}