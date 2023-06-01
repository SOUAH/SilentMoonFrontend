package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName

data class MeditationModel(
    @SerialName("MeditationName")
    val name: String,
    @SerialName("topic")
    val topic: String,
    @SerialName("meditationImageUrl")
    val imageUrl: String,
    @SerialName("meditationDescription")
    val description: String,
    @SerialName("Playlist")
    val playlist: Array<String>
)