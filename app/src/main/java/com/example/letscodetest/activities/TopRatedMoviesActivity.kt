package com.example.letscodetest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.letscodetest.databinding.ActivityTopRatedMoviesBinding
import com.example.letscodetest.repositories.db.MovieEntity
import com.example.letscodetest.utils.OnClickMovieAdapterListener
import com.example.letscodetest.utils.adapters.MovieAdapter
import com.example.letscodetest.viewmodels.TopRatedMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopRatedMoviesActivity : AppCompatActivity() {

    private var movieAdapter: MovieAdapter? = null
    private lateinit var binding: ActivityTopRatedMoviesBinding
    private val viewModel: TopRatedMoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopRatedMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupMovieList()
    }


    fun setupMovieList() = with(viewModel){
        movieAdapter = MovieAdapter()
        binding.rcvToPRatedMovies.layoutManager = GridLayoutManager(baseContext,3)

        movieAdapter?.apply {
            setOnClickAdapterListener(object : OnClickMovieAdapterListener {
                override fun setOnClickFavoriteListener(movie: MovieEntity) {
                    favoriteMovie(movie)
                    binding.rcvToPRatedMovies.adapter?.notifyDataSetChanged()
                }

                override fun setOnClickUnfavoriteListener(Id: Long) {
                    unfavoriteMovie(Id)
                    binding.rcvToPRatedMovies.adapter?.notifyDataSetChanged()
                }

            })
        }
        getMoviesFromServer()
            .observe(this@TopRatedMoviesActivity) { pagingMovies ->
                movieAdapter?.submitData(lifecycle, pagingMovies)
            }

        binding.rcvToPRatedMovies.adapter = movieAdapter
    }
}