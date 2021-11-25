package com.example.gamesapp.data.entity.game

import com.google.gson.annotations.SerializedName

data class AddedByStatus(
    @SerializedName("owned") var owned: Int,
    @SerializedName("beaten") var beaten: Int,
    @SerializedName("dropped") var dropped: Int
)