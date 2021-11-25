package com.example.gamesapp.data.entity

import com.example.gamesapp.data.entity.game.*
import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("slug") var slug: String,
    @SerializedName("name") var name: String,
    @SerializedName("released") var released: String,
    @SerializedName("tba") var tba: Boolean,
    @SerializedName("description") var description: String,
    @SerializedName("background_image") var backgroundImage: String?,
    @SerializedName("rating") var rating: Double,
    @SerializedName("rating_top") var ratingTop: Int?,
    @SerializedName("ratings") var ratings: List<Ratings>,
    @SerializedName("ratings_count") var ratingsCount: Int?,
    @SerializedName("reviews_text_count") var reviewsTextCount: String?,
    @SerializedName("added") var added: Int,
    @SerializedName("added_by_status") var addedByStatus: AddedByStatus = AddedByStatus(0, 0, 0),
    @SerializedName("metacritic") var metacritic: Int?,
    @SerializedName("playtime") var playtime: Int,
    @SerializedName("suggestions_count") var suggestionsCount: Int?,
    @SerializedName("updated") var updated: String,
    @SerializedName("esrb_rating") var esrbRating: EsrbRating?,
    @SerializedName("platforms") var platforms: List<Platforms>,
    @SerializedName("publishers") var publishers: List<Publishers>,
    @SerializedName("genres") var genres: List<Genres>
)














