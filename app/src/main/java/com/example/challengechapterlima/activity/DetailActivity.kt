package com.example.challengechapterlima.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.challengechapterlima.R
import com.example.challengechapterlima.databinding.ActivityDetailBinding
import com.example.challengechapterlima.model.MovieDetailData
import com.example.challengechapterlima.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    var b:Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        b = intent.extras
        val i = b?.getString("imdbid")
        val apikey = "76642201"

        RetrofitClient.instances.getDetailMovie(i,apikey).enqueue(object : Callback<MovieDetailData>{
            override fun onResponse(
                call: Call<MovieDetailData>,
                response: Response<MovieDetailData>
            ) {
//               binding.tvTahun.text = response.body()?.Year
                binding.tvDeskripsi.text = response.body()?.deskripsi
                binding.tvJudulmovie.text = response.body()?.Title
                binding.tvTglrilis.text = response.body()?.rilis

                Glide.with(this@DetailActivity).load(response.body()?.gambar).into(binding.imgGambarposter)

            }

            override fun onFailure(call: Call<MovieDetailData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
}