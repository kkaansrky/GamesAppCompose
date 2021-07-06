package com.example.gamesapp

import com.google.gson.annotations.SerializedName


data class GamesResponse(

    @SerializedName("count") var count: Int,
    @SerializedName("next") var next: String,
    @SerializedName("previous") var previous: String,
    @SerializedName("results") var results: List<GameResponse>

)


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

data class Publishers(

    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("slug") var slug: String,
    @SerializedName("games_count") var gamesCount: Int,
    @SerializedName("image_background") var imageBackground: String

)

data class Genres(

    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("slug") var slug: String,
    @SerializedName("games_count") var gamesCount: Int,
    @SerializedName("image_background") var imageBackground: String

)

data class Ratings(

    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("count") var count: Int,
    @SerializedName("percent") var percent: Double

)

data class AddedByStatus(

    @SerializedName("owned") var owned: Int,
    @SerializedName("beaten") var beaten: Int,
    @SerializedName("dropped") var dropped: Int

)


data class Platform(

    @SerializedName("id") var id: Int,
    @SerializedName("slug") var slug: String,
    @SerializedName("name") var name: String

)

data class Requirements(

    @SerializedName("minimum") var minimum: String?,
    @SerializedName("recommended") var recommended: String?

)

data class Platforms(

    @SerializedName("platform") var platform: Platform,
    @SerializedName("released_at") var releasedAt: String?,
    @SerializedName("requirements") var requirements: Requirements

)

data class EsrbRating(

    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("slug") var slug: String

)

