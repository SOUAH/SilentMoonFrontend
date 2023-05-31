package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName

//list of topics
data class TopicListModel(
//    val title: String,
//    val height: Int,
//    @DrawableRes val img_topic: Int

    @SerialName("topics")
     val topics : Array<TopicModel>,
)