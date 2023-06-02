package com.nistruct.meditation.retrofit

import com.nistruct.meditation.data.entity.*
import retrofit2.Response
import retrofit2.http.*

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

    @GET("/v1/meditation")
    suspend fun getMeditationList(
        @Header("Authorization") authHeader: String
    ): Response<MeditationListModel>

    @PUT("/v1/user/{id}")
    suspend fun updateUser(
        @Header("Authorization") authHeader: String,
        @Path("id") id: String,
        @Body body: SetUserPreferencesRequestModel
    ): Response<UserResponse>
}