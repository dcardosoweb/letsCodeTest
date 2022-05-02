package com.example.letscodetest.utils.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.letscodetest.repositories.db.MusicEntity
import com.example.letscodetest.utils.OnClickMusicAdapterListener
import com.example.letscodetest.utils.viewholders.MusicViewHolder

class MusicAdapter : PagingDataAdapter<MusicEntity, MusicViewHolder>(DIFF_CALLBACK) {

    private var onClickMusicAdapterListener: OnClickMusicAdapterListener? = null

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        getItem(position)?.let { item -> holder.bidingMusic(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder = MusicViewHolder.create(
        parent,
        onClickMusicAdapterListener
    )

    fun setOnClickAdapterListener(onClickMusicAdapterListener: OnClickMusicAdapterListener) {
        this.onClickMusicAdapterListener = onClickMusicAdapterListener
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MusicEntity>() {
            override fun areItemsTheSame(
                oldItem: MusicEntity,
                newItem: MusicEntity
            ): Boolean = oldItem.trackId == newItem.trackId &&
                    oldItem.clipUrl == newItem.clipUrl

            override fun areContentsTheSame(
                oldItem: MusicEntity,
                newItem: MusicEntity
            ): Boolean = oldItem.trackId == newItem.trackId &&
                    oldItem.clipUrl == newItem.clipUrl
        }
    }
}