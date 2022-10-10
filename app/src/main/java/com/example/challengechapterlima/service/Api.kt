package com.example.challengechapterlima.service

import com.example.challengechapterlima.model.MovieData
import com.example.challengechapterlima.model.MovieDetailData
import com.example.challengechapterlima.model.ResponseDataUserItem
import com.example.challengechapterlima.model.SearchData
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("?s=marvel&apikey=76642201")
    fun getMovies() : Call<SearchData>

    @GET("/")
    fun getSearch(
        @Query("s") s: String?,
        @Query("apikey") apikey:String
    ) : Call<SearchData>

    @GET("/")
    fun getDetailMovie(
        @Query("i") i:String?,
        @Query("apikey") apikey: String,
    ) : Call<MovieDetailData>


}