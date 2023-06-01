package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName


data class MeditationListModel(

    @SerialName("meditation")
    val meditations: Array<MeditationModel>,
)