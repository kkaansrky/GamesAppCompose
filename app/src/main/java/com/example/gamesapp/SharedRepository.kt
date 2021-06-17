package com.example.gamesapp

class SharedRepository {
    suspend fun getGameById(gameId: Int): GamesResponse? {
        val request = NetworkLayer.apiClient.getGameById(gameId)

        if (request.failed){
            return null
        }

        if (!request.isSuccesful){
            return null
        }

        return request.body
    }

}