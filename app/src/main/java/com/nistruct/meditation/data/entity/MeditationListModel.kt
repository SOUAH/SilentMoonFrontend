package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName


data class MeditationListModel(

    @SerialName("meditations")
    val meditations: Array<MeditationModel>,
)