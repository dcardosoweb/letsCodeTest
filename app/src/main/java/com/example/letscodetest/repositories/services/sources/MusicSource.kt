package com.example.letscodetest.repositories.services.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.letscodetest.repositories.db.IFavoriteMusicDB
import com.example.letscodetest.repositories.db.MusicEntity
import com.example.letscodetest.repositories.services.ITheAudioDBService
import com.example.letscodetest.repositories.services.responses.asEntity
import com.example.letscodetest.utils.ResponseNoBodyException

class MusicSource (private val theAudioDBService: ITheAudioDBService, private val favoriteMusicDB: IFavoriteMusicDB, val userId:String) : PagingSource<Int, MusicEntity>() {

    override fun getRefreshKey(state: PagingState<Int, MusicEntity>): Int? =
        state.anchorPosition?.let { state.closestItemToPosition(it)?.id?.toInt() }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MusicEntity> {
        return try {

            val response = theAudioDBService.getMusic("111279")

            val musicList = response?.body()?.mvids?.asEntity()

            musicList?.let {

                for (item in it){
                    item.favorite = favoriteMusicDB.checkFavoriteUserMusic(userId,item.trackId)!=null
                }

                LoadResult.Page(
                    it,
                    null,
                    null
                )
            } ?: throw ResponseNoBodyException()
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}