package com.example.gamesapp

class SharedRepository {
    suspend fun getGameById(gameId: Int, apiKey: String): GameResponse? {
        val request = NetworkLayer.apiClient.getGameById(gameId, apiKey)

        if (request.failed) {
            return null
        }

        if (!request.isSuccesful) {
            return null
        }

        return request.body
    }

    suspend fun getGames(
        search: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): SimpleResponse<GamesResponse> {
        val request = NetworkLayer.apiClient.getGames(search,page,pageSize,apiKey)


        return request
    }

}