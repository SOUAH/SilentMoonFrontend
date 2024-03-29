package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeditationListModel(

    @SerialName("meditations")
    val meditations: Array<MeditationModel>,
)