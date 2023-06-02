package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName

data class SetUserPreferencesRequestModel(
    @SerialName("favoriteTopic")
    val favoriteTopic: String,

    @SerialName("notificationDays")
    val notificationDays: Array<String>,

    @SerialName("notificationTime")
    val notificationTime: String,
)
