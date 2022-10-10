package com.example.challengechapterlima.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengechapterlima.R
import com.example.challengechapterlima.adapter.MovieAdapter
import com.example.challengechapterlima.databinding.FragmentMovieBinding
import com.example.challengechapterlima.model.MovieData
import com.example.challengechapterlima.model.SearchData
import com.example.challengechapterlima.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<MovieData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_movie, container, false)

        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager = LinearLayoutManager(context)



//        binding.progressBar.visibility

        showData()
        searchData()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showData() {
    RetrofitClient.instances.getMovies().enqueue(
    object : Callback<SearchData> {
        override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
            val responseCode = response.code()
//                val cekRes = response.body()?.res

//                if (cekRes == "True") {
            response.body()?.let { list.addAll(it.data) }
            val adapter = MovieAdapter(list, requireContext())
            binding.rvData.adapter = adapter
//                    binding.progressBar.isVisible = false
        }
        //                else {
//                    Toast.makeText(context, "Movie not found", Toast.LENGTH_SHORT).show()
//                        binding.progressBar.isVisible = false
//                }
//            }

        override fun onFailure(call: Call<SearchData>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })
}

    fun searchData() {
//        Search data
        val bundle = arguments
        val s = bundle?.getString("carimovie")
        val apikey = "76642201"
        RetrofitClient.instances.getSearch(s,apikey).enqueue(
            object : Callback<SearchData> {
                override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
//                    val responseCode = response.code()
                val cekRes = response.body()?.res

                if (cekRes == "True") {
                    response.body()?.let { list.addAll(it.data) }
                    val adapter = MovieAdapter(list, requireContext())
                    binding.rvData.adapter = adapter
//                    binding.progressBar.isVisible = false
                }
                                else {
                    Toast.makeText(context, "Movie not found", Toast.LENGTH_SHORT).show()
//                        binding.progressBar.isVisible = false
                }
            }

                override fun onFailure(call: Call<SearchData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}