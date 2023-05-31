package com.nistruct.meditation.retrofit

import com.nistruct.meditation.data.entity.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MeditationAPI {

    @POST("/v1/auth/signup")
    suspend fun signUp(
        @Body body: RegisterRequestModel
    ): Response<RegisterResponse>

    @GET("/v1/user/current-user")
    suspend fun getCurrentUser(
        @Header("Authorization") authHeader: String
    ): Response<UserResponse>

    @POST("/v1/auth/login")
    suspend fun signIn(@Body body: LoginRequestModel): Response<LoginResponse>

    @POST("/v1/auth/forgot-password")
    suspend fun forgotPassword(@Body body: ForgotPasswordRequest)

    @GET("/v1/topic/")
    suspend fun getTopicList(
        @Header("Authorization") authHeader: String
    ): Response<TopicListModel>

}