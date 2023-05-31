package com.nistruct.meditation.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RegisterRequestModel(
    @SerialName("email")
    val email : String,
    @SerialName("password")
    val password : String,
    @SerialName("username")
    val username : String
)