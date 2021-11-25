package com.example.gamesapp.data.entity.game

import com.google.gson.annotations.SerializedName

data class Publishers(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("slug") var slug: String,
    @SerializedName("games_count") var gamesCount: Int,
    @SerializedName("image_background") var imageBackground: String
)