package com.example.gamesapp.data.entity.game

import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("id") var id: Int,
    @SerializedName("slug") var slug: String,
    @SerializedName("name") var name: String
)

