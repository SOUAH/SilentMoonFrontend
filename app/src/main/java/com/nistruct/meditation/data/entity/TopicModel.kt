package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName

data class TopicModel(
    @SerialName("topicName")
    val topicName: String,
    @SerialName("topicShortName")
    val topicShortName: String,
    @SerialName("topicImageUrl")
    val topicImageUrl: String
)