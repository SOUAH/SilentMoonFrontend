package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RegisterResponse(
    @SerialName("token")
    val token : String,
    )