package com.example.challengechapterlima.model

import com.google.gson.annotations.SerializedName

data class MovieDetailData(
    val Year : String,
    val Title:String,
    @SerializedName("Released") val rilis: String,
    @SerializedName("Poster") val gambar: String,
    @SerializedName("Plot") val deskripsi : String,
)
