package com.example.letscodetest.repositories.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class MusicEntity() {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var userId: String? = null
    var trackId: String = ""
    var clipUrl: String?=null
    var name:String?=null
    var trackThumb:String?=null
    @Ignore
    var favorite:Boolean=false

    constructor(trackId: String, clipUrl: String, name:String, trackThumb:String?) : this() {
        this.trackId = trackId
        this.clipUrl = clipUrl
        this.name = name
        this.trackThumb = trackThumb
    }
}