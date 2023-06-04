package com.nistruct.meditation.data.repo


import androidx.lifecycle.MutableLiveData
import com.nistruct.meditation.data.AppDataStore.DataStoreInterface
import com.nistruct.meditation.data.entity.MeditationModel
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeditationRepository @Inject constructor(
    val apiInteractor: ApiInteractor,
    val dataStore: DataStoreInterface
) {

    var meditationList = MutableLiveData<Array<MeditationModel>>()

    init {
        meditationList = MutableLiveData(null)
    }

    fun returnMeditationList(): MutableLiveData<Array<MeditationModel>> {
        return meditationList
    }

    suspend fun getAllMeditations() {
        try {
            if (!dataStore.getAccessToken().isNullOrEmpty()) {
                val getMeditations = apiInteractor.getMeditationList(dataStore.getAccessToken().toString())

                this.meditationList.value = getMeditations?.meditations

                getMeditations?.meditations?.let {
                    dataStore.putMeditationList(it)
                }
            }
        } catch (t: Throwable) {
            Timber.i("TAG: ${t.message}")
        }
    }
}