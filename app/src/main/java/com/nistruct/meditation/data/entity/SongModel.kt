package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName

data class SongModel(
    @SerialName("songName")
    val songName: String,
    @SerialName("songUrl")
    val songUrl: String,
)