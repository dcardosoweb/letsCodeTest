package com.example.letscodetest.utils.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.letscodetest.R
import com.example.letscodetest.databinding.ItemListBinding
import com.example.letscodetest.repositories.db.MovieEntity
import com.example.letscodetest.utils.OnClickMovieAdapterListener

class MovieViewHolder(private val binding: ItemListBinding,
                      private val onClickMovieAdapterListener: OnClickMovieAdapterListener?) : RecyclerView.ViewHolder(binding.root) {


    companion object {
        fun create(
            parent: ViewGroup,
            onClickMovieAdapterListener: OnClickMovieAdapterListener?
        ): MovieViewHolder {
            val binding = ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return MovieViewHolder(
                binding,
                onClickMovieAdapterListener
            )
        }
    }

    fun bidingMovie( movie: MovieEntity) = with(movie) {
        setupMovie(movie)
    }

    fun setupMovie(movie: MovieEntity)= binding.apply {

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500"+movie.posterUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageViewItem)

        if(movie.favorite)
            imageViewFavorite.setImageResource( R.drawable.ic_favorite)
        else
            imageViewFavorite.setImageResource(R.drawable.ic_not_favorite)

        imageViewFavorite.setOnClickListener {
            if(movie.favorite) {
                movie.favorite = false
                onClickMovieAdapterListener?.setOnClickUnfavoriteListener(movie.id)
            }
            else {
                movie.favorite = true
                onClickMovieAdapterListener?.setOnClickFavoriteListener(movie)
            }
        }

    }
}