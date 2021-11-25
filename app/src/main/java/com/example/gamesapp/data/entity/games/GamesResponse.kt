package com.example.gamesapp.data.entity.games

import com.example.gamesapp.data.entity.GameResponse
import com.google.gson.annotations.SerializedName


data class GamesResponse(
    @SerializedName("count") var count: Int,
    @SerializedName("next") var next: String,
    @SerializedName("previous") var previous: String,
    @SerializedName("results") var results: List<GameResponse>
)




