package com.example.letscodetest.repositories.services.responses

import com.example.letscodetest.repositories.db.MusicEntity
import com.google.gson.annotations.SerializedName

class GetMusicResponse (
    @SerializedName( "mvids")
    val mvids: List<Mvid>
) {
    data class Mvid (
        val idArtist: String,
        val idAlbum: String,
        @SerializedName( "idTrack")
        val idTrack: String,
        @SerializedName( "strTrack")
        val strTrack: String,
        @SerializedName( "strTrackThumb")
        val strTrackThumb: String? = null,
        @SerializedName( "strMusicVid")
        val strMusicVid: String,
        val strDescriptionEN: String? = null
    )
}

fun GetMusicResponse.Mvid.asEntity(): MusicEntity {
    return MusicEntity( idTrack, strMusicVid, strTrack, strTrackThumb)
}

fun List<GetMusicResponse.Mvid>.asEntity(): List<MusicEntity> {
    return map {
        it.asEntity()
    }
}