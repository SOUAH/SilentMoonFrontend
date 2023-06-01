package com.nistruct.meditation.data.repo

import com.nistruct.meditation.data.entity.*

interface ApiInteractor {

    suspend fun registerUser(request: RegisterRequestModel): RegisterResponse?

    suspend fun logInUser(request: LoginRequestModel): LoginResponse?

    suspend fun getCurrentUser(authHeader: String): UserResponse?

    suspend fun forgotPassword(requestBody: ForgotPasswordRequest)

    suspend fun getTopicList(authHeader: String): TopicListModel?

    suspend fun getMeditationList(authHeader: String): MeditationListModel?

}