package com.example.letscodetest.utils


import com.example.letscodetest.repositories.db.MusicEntity

interface OnClickMusicAdapterListener {
    fun setOnClickFavoriteListener(music: MusicEntity)
    fun setOnClickUnfavoriteListener(Id: Long)
}