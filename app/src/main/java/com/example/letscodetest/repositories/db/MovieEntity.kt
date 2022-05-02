package com.example.letscodetest.repositories.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class MovieEntity() {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var userId: String? = null
    var movieId: Long = 0
    var posterUrl: String?=null
    @Ignore
    var favorite:Boolean=false

    constructor(movieId: Long, posterUrl: String) : this() {
        this.movieId = movieId
        this.posterUrl = posterUrl
    }
}