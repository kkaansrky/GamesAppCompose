package com.example.gamesapp.data.entity.game

import com.google.gson.annotations.SerializedName

data class Ratings(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("count") var count: Int,
    @SerializedName("percent") var percent: Double
)