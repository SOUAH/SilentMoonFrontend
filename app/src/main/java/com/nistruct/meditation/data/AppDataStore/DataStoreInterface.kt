package com.nistruct.meditation.data.AppDataStore

import com.nistruct.meditation.data.entity.MeditationModel
import com.nistruct.meditation.data.entity.TopicModel
import com.nistruct.meditation.data.entity.UserResponse

interface DataStoreInterface {

    suspend fun putAccessToken(value: String)

    suspend fun getAccessToken(): String?

    suspend fun deleteAccessToken()

    suspend fun putUserResponse(userResponse: UserResponse)

    suspend fun getUserResponse(): UserResponse?

    suspend fun getUserId(): String?

    suspend fun getUserName(): String?

    suspend fun getUserEmail(): String?

    suspend fun getUserNotificationTime(): String?

    suspend fun getUserFavoriteTopic(): String?

    suspend fun getUserNotificationDays(): Array<String>?

    suspend fun putTopicList(topicList: Array<TopicModel>)

    suspend fun putMeditationList(meditationList: Array<MeditationModel>)

    suspend fun deleteUserResponse()

}