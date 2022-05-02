package com.example.letscodetest.repositories.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IFavoriteMusicDB {

    @Query("SELECT * FROM MusicEntity where userId= :userId")
    fun getUserFavoriteMusic(userId:String): List<MusicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie : MusicEntity)

    @Query("DELETE FROM MusicEntity where id= :id")
    fun unfavoriteMusic(id:Long)

    @Query("SELECT * FROM MusicEntity where userId= :userId and trackId=:trackId")
    fun checkFavoriteUserMusic(userId:String,trackId:String): MusicEntity?
}