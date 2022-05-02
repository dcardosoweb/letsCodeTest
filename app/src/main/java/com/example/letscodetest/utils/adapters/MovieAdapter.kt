package com.example.letscodetest.utils.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.letscodetest.repositories.db.MovieEntity
import com.example.letscodetest.utils.OnClickMovieAdapterListener
import com.example.letscodetest.utils.viewholders.MovieViewHolder

class MovieAdapter: PagingDataAdapter<MovieEntity, MovieViewHolder>(DIFF_CALLBACK) {

    private var onClickMovieAdapterListener: OnClickMovieAdapterListener? = null

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        getItem(position)?.let { item -> holder.bidingMovie(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder= MovieViewHolder.create(
        parent,
        onClickMovieAdapterListener
    )

    fun setOnClickAdapterListener(onClickMovieAdapterListener: OnClickMovieAdapterListener) {
        this.onClickMovieAdapterListener = onClickMovieAdapterListener
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean = oldItem.movieId == newItem.movieId &&
                    oldItem.posterUrl == newItem.posterUrl

            override fun areContentsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean = oldItem.movieId == newItem.movieId &&
                    oldItem.posterUrl == newItem.posterUrl
        }
    }
}