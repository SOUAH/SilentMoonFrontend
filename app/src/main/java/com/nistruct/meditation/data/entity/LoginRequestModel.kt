package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName

data class LoginRequestModel(
    @SerialName("email")
    val email : String,

    @SerialName("password")
    val password : String,
)
