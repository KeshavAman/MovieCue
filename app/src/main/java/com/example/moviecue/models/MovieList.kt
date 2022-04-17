package com.example.moviecue.models

import com.google.gson.annotations.SerializedName

data class MovieList(

    @field:SerializedName("Response")
    val response: String? = null,

    @field:SerializedName("totalResults")
    val totalResults: String? = null,

    @field:SerializedName("Search")
    val search: List<SearchItem?>? = null
) {
    override fun toString(): String {
        return "MovieList(response=$response, totalResults=$totalResults, search=$search)"
    }
}

data class SearchItem(

    @field:SerializedName("Type")
    val type: String? = null,

    @field:SerializedName("Year")
    val year: String? = null,

    @field:SerializedName("imdbID")
    val imdbID: String? = null,

    @field:SerializedName("Poster")
    val poster: String? = null,

    @field:SerializedName("Title")
    val title: String? = null
)