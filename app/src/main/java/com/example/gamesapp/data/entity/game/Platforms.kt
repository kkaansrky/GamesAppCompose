package com.example.gamesapp.data.entity.game

import com.google.gson.annotations.SerializedName

data class Platforms(
    @SerializedName("platform") var platform: Platform,
    @SerializedName("released_at") var releasedAt: String?,
    @SerializedName("requirements") var requirements: Requirements
)