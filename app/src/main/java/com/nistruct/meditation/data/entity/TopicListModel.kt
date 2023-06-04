package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName

//list of topics
data class TopicListModel(

    @SerialName("topics")
    val topics: Array<TopicModel>,
)