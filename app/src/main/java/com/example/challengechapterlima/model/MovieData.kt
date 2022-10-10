package com.example.challengechapterlima.model

import com.google.gson.annotations.SerializedName

data class MovieData(
    val Year : String,
    val Title:String,
    @SerializedName("Poster") val gambar: String,
    @SerializedName("imdbID") val idmovie: String
)
