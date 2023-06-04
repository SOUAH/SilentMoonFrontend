package com.nistruct.meditation.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MeditationModel(
    @SerializedName("_id")
    val id: String,
    @SerializedName("meditationName")
    val meditationName: String,
    @SerializedName("topicName")
    val topicName: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("playlist")
    val playlist: Array<SongModel>
)