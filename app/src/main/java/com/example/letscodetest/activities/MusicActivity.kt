package com.example.letscodetest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.letscodetest.databinding.ActivityMusicBinding
import com.example.letscodetest.databinding.ActivityTopRatedMoviesBinding
import com.example.letscodetest.repositories.db.MovieEntity
import com.example.letscodetest.repositories.db.MusicEntity
import com.example.letscodetest.utils.OnClickMovieAdapterListener
import com.example.letscodetest.utils.OnClickMusicAdapterListener
import com.example.letscodetest.utils.adapters.MovieAdapter
import com.example.letscodetest.utils.adapters.MusicAdapter
import com.example.letscodetest.viewmodels.MusicViewModel
import com.example.letscodetest.viewmodels.TopRatedMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MusicActivity : AppCompatActivity() {

    private var musicAdapter: MusicAdapter? = null
    private lateinit var binding: ActivityMusicBinding
    private val viewModel: MusicViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupMovieList()
    }

    fun setupMovieList() = with(viewModel){
        musicAdapter = MusicAdapter()
        binding.rcvMusic.layoutManager =  LinearLayoutManager(baseContext)

        musicAdapter?.apply {
            setOnClickAdapterListener(object : OnClickMusicAdapterListener {
                override fun setOnClickFavoriteListener(music: MusicEntity) {
                    favoriteMusic(music)
                    binding.rcvMusic.adapter?.notifyDataSetChanged()
                }

                override fun setOnClickUnfavoriteListener(Id: Long) {
                    unfavoriteMusic(Id)
                    binding.rcvMusic.adapter?.notifyDataSetChanged()
                }

            })
        }
        getMusicFromServer()
            .observe(this@MusicActivity) { pagingMusic ->
                musicAdapter?.submitData(lifecycle, pagingMusic)
            }

        binding.rcvMusic.adapter = musicAdapter
    }
}