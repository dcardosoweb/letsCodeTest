package com.example.letscodetest.repositories.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IFavoriteMovieDB {

    @Query("SELECT * FROM MovieEntity where userId= :userId")
    fun getUserFavoriteMovies(userId:String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie : MovieEntity)

    @Query("DELETE FROM MovieEntity where id= :id")
    fun unfavoriteMovie(id:Long)

    @Query("SELECT * FROM MovieEntity where userId= :userId and movieId=:movieId")
    fun checkFavoriteUserMovie(userId:String,movieId:Long): MovieEntity?

}