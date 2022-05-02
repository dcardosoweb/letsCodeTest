package com.example.letscodetest.repositories.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        MovieEntity::class,
        MusicEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DataBaseApplication : RoomDatabase() {
    abstract fun favoriteMovieDB(): IFavoriteMovieDB
    abstract fun favoriteMusicDB(): IFavoriteMusicDB

    companion object {
        private const val DATABASE_NAME = "lets_code"
        private var INSTANCE: DataBaseApplication? = null

        fun getInstance(context: Context): DataBaseApplication? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseApplication::class.java, DATABASE_NAME
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return INSTANCE
        }
    }
}