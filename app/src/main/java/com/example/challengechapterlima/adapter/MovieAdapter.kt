package com.example.challengechapterlima.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapterlima.activity.DetailActivity
import com.example.challengechapterlima.databinding.ItemMovieBinding
import com.example.challengechapterlima.model.MovieData

class MovieAdapter(   private val listMovie  : ArrayList<MovieData>, private val context : Context): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView : ItemMovieBinding):RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView
        fun bind(movieData: MovieData){
            with(binding) {
                Glide.with(itemView).load(movieData.gambar).into(imgPoster)
                tvTitle.text = movieData.Title
                tvYear.text = movieData.Year

//                detail
                cvIdmovie.setOnClickListener {
                    var i = Intent(context, DetailActivity::class.java).apply {
                        putExtra("imdbid", movieData.idmovie)
                    }
                    context.startActivity(i)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }


}