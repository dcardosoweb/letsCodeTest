package com.example.letscodetest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.letscodetest.repositories.TheAudioDBRepository
import com.example.letscodetest.repositories.db.MusicEntity
import kotlinx.coroutines.launch

class MusicViewModel (
    private val audioRepository: TheAudioDBRepository
): ViewModel() {

    fun getMusicFromServer() = audioRepository
        .getMusicFromServer()
        .cachedIn(viewModelScope)

    fun favoriteMusic(
        music : MusicEntity
    ) = viewModelScope.launch {
        audioRepository.favoriteMusic(music)
    }

    fun unfavoriteMusic(
        id : Long
    ) = viewModelScope.launch {
        audioRepository.unfavoriteMusic(id)
    }
}