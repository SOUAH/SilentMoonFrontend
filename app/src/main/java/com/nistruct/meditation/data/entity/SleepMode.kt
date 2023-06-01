package com.nistruct.meditation.data.entity

import androidx.annotation.DrawableRes

data class SleepMode(
    val title: String,
    val detail: String,
    @DrawableRes val icon_id: Int
)
