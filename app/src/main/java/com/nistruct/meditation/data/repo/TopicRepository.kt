package com.nistruct.meditation.data.repo


import androidx.lifecycle.MutableLiveData
import com.nistruct.meditation.data.AppDataStore.DataStoreInterface
import com.nistruct.meditation.data.entity.TopicModel
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopicRepository @Inject constructor(
    val apiInteractor: ApiInteractor,
    val dataStore: DataStoreInterface
) {

    var topicList = MutableLiveData<Array<TopicModel>>()

    fun returntopicList(): MutableLiveData<Array<TopicModel>> {
        return topicList
    }

    suspend fun getAllTopics() {
        try {
            if (!dataStore.getAccessToken().isNullOrEmpty()) {
                val getTopics =
                    apiInteractor.getTopicList(dataStore.getAccessToken().toString())

                this.topicList.value = getTopics?.topics
            }
        } catch (t: Throwable) {
            Timber.i("TAG: ${t.message}")
        }
    }
}