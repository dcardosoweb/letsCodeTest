package com.example.letscodetest.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.letscodetest.repositories.db.IFavoriteMusicDB
import com.example.letscodetest.repositories.db.MusicEntity
import com.example.letscodetest.repositories.services.ITheAudioDBService
import com.example.letscodetest.repositories.services.sources.MusicSource
import com.example.letscodetest.utils.SessionUser

class TheAudioDBRepository (val musicService: ITheAudioDBService, val favoriteMusicDB: IFavoriteMusicDB) {

    fun getMusicFromServer() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            MusicSource(
                musicService,favoriteMusicDB, SessionUser.userId!!)
        }
    ).liveData

    fun favoriteMusic(musicEntity: MusicEntity){
        musicEntity.userId = SessionUser.userId
        favoriteMusicDB.insert(musicEntity)
    }

    fun unfavoriteMusic(id:Long){
        favoriteMusicDB.unfavoriteMusic(id)
    }
}