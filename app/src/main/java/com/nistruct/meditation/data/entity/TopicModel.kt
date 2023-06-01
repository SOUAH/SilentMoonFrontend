package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName

data class TopicModel(
    @SerialName("topicName")
    val name: String,
    @SerialName("topicShortName")
    val shortName: String,
    @SerialName("topicImageUrl")
    val imageUrl: String
)