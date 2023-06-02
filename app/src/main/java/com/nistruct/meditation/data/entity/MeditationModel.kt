package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName

data class MeditationModel(
    @SerialName("meditationName")
    val meditationName: String,
    @SerialName("topicName")
    val topicName: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("description")
    val description: String,
    @SerialName("playlist")
    val playlist: Array<SongModel>
)