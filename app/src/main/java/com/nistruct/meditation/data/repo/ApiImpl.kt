package com.nistruct.meditation.data.repo

import com.nistruct.meditation.data.entity.*
import com.nistruct.meditation.retrofit.MeditationAPI
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject


class ApiImpl
@Inject constructor(
    private val meditationAPI: MeditationAPI,
) : ApiInteractor {

    override suspend fun registerUser(request: RegisterRequestModel): RegisterResponse? {
        val registerResponse = meditationAPI.signUp(request)
        Timber.tag("API_TEST").i(registerResponse.code().toString())
        return if (registerResponse.isSuccessful) {
            registerResponse.body()
        } else throw HttpException(registerResponse)
    }

    override suspend fun logInUser(request: LoginRequestModel): LoginResponse? {
        val registerResponse = meditationAPI.signIn(request)
        Timber.tag("API_TEST_LOG_IN").i(registerResponse.code().toString())
        return if (registerResponse.isSuccessful) {
            registerResponse.body()
        } else throw HttpException(registerResponse)
    }

    //implementation of interfacze
    override suspend fun forgotPassword(request: ForgotPasswordRequest) {
        meditationAPI.forgotPassword(request)
        Timber.tag("API_TEST_FORGOT_PASSWORD").i("Forgot password request is sent")
    }

    override suspend fun getCurrentUser(authHeader: String): UserResponse? {
        val userResponse = meditationAPI.getCurrentUser("Bearer " + authHeader)
        Timber.tag("API_TEST_GET_CURRENT_USER").i(userResponse.code().toString())
        return if (userResponse.isSuccessful) {
            userResponse.body()
        } else throw HttpException(userResponse)
    }

    override suspend fun getTopicList(authHeader: String): TopicListModel? {
        val topicListResponse = meditationAPI.getTopicList("Bearer " + authHeader)
        Timber.tag("API_TEST_GET_LIST_OF_TOPICS").i(topicListResponse.code().toString())
        return if (topicListResponse.isSuccessful) {
            topicListResponse.body()
        } else throw HttpException(topicListResponse)
    }

    override suspend fun getMeditationList(authHeader: String): MeditationListModel? {
        val meditationListResponse = meditationAPI.getMeditationList("Bearer " + authHeader)
        Timber.tag("API_TEST_GET_LIST_OF_MEDITATIONS").i(meditationListResponse.code().toString())
        return if (meditationListResponse.isSuccessful) {
            meditationListResponse.body()
        } else throw HttpException(meditationListResponse)
    }
}