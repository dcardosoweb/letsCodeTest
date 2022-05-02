package com.example.letscodetest.utils.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.letscodetest.R
import com.example.letscodetest.databinding.MusicItemListBinding
import com.example.letscodetest.repositories.db.MusicEntity
import com.example.letscodetest.utils.OnClickMusicAdapterListener

class MusicViewHolder (private val binding: MusicItemListBinding,
                       private val onClickMusicAdapterListener: OnClickMusicAdapterListener?) : RecyclerView.ViewHolder(binding.root) {


    companion object {
        fun create(
            parent: ViewGroup,
            onClickMovieAdapterListener: OnClickMusicAdapterListener?
        ): MusicViewHolder {
            val binding = MusicItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return MusicViewHolder(
                binding,
                onClickMovieAdapterListener
            )
        }
    }

    fun bidingMusic( music: MusicEntity) = with(music) {
        setupMusic(music)
    }

    fun setupMusic(music: MusicEntity)= binding.apply {

        textViewMusicName.text = music.name

        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_music_place_holder)
            .error(R.drawable.ic_music_place_holder)

        Glide.with(itemView.context)
            .load(music.trackThumb)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageViewMusicClip)

        if(music.favorite)
            imageViewMusicFavorite.setImageResource( R.drawable.ic_favorite)
        else
            imageViewMusicFavorite.setImageResource(R.drawable.ic_not_favorite)

        imageViewMusicFavorite.setOnClickListener {
            if(music.favorite) {
                music.favorite = false
                onClickMusicAdapterListener?.setOnClickUnfavoriteListener(music.id)
            }
            else {
                music.favorite = true
                onClickMusicAdapterListener?.setOnClickFavoriteListener(music)
            }
        }

    }
}