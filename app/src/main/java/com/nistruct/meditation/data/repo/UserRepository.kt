package com.nistruct.meditation.data.repo


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.nistruct.meditation.data.AppDataStore.DataStoreInterface
import com.nistruct.meditation.data.entity.ForgotPasswordRequest
import com.nistruct.meditation.data.entity.LoginRequestModel
import com.nistruct.meditation.data.entity.RegisterRequestModel
import com.nistruct.meditation.data.entity.SetUserPreferencesRequestModel
import timber.log.Timber
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    val apiInteractor: ApiInteractor,
    val dataStore: DataStoreInterface
) {

    var accessToken = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var userName = MutableLiveData<String>()

    var favTopic = MutableLiveData<String>()
    var notificationTime = MutableLiveData<LocalTime>()
    var notificationDays = MutableLiveData<Array<String>>()

    init {
        email = MutableLiveData<String>("")
        userName = MutableLiveData<String>("")
        accessToken = MutableLiveData<String>("")

        var favTopic = MutableLiveData<String>("")
        var notificationTime = MutableLiveData<LocalTime>(null)
        var notificationDays = MutableLiveData<Array<String>>( null)
    }

    fun returnEmail(): MutableLiveData<String> {
        return email
    }

    fun returnUsername(): MutableLiveData<String> {
        return userName
    }

    fun returnToken(): MutableLiveData<String> {
        return accessToken
    }

    fun returnNotificationTime(): MutableLiveData<LocalTime> {
        return notificationTime
    }

    fun returnFavTopic(): MutableLiveData<String> {
        return favTopic
    }

    fun returnNotificationDays(): MutableLiveData<Array<String>> {
        return notificationDays
    }
    suspend fun registerUser(email: String, username: String, password: String) {
        val registeringRequest = RegisterRequestModel(
            email = email,
            username = username,
            password = password
        )
        try {
            val registeredUser = apiInteractor.registerUser(registeringRequest)
            this.accessToken.value = registeredUser?.token

            if (!registeredUser?.token.isNullOrEmpty()) {
                dataStore.putAccessToken(registeredUser?.token.toString())
            }

            Timber.tag("token").i("token: ${registeredUser?.token} ")
        } catch (t: Throwable) {

            Timber.i("TAG: ${t.message}")
        }
    }

    suspend fun getCurrentUser() {
        try {
            if (accessToken.value.toString() != "") {
                val currentUser =
                    apiInteractor.getCurrentUser(dataStore.getAccessToken().toString())

                if (currentUser != null) {
                    dataStore.putUserResponse(currentUser)
                }

                this.email.value = currentUser?.email
                this.userName.value = currentUser?.userName

                var notificationTime = LocalTime.now().plusMinutes(5);
                if (!currentUser?.notificationTime.isNullOrEmpty()){
                    notificationTime = LocalTime.parse(currentUser?.notificationTime)
                }

                this.favTopic.value = currentUser?.favoriteTopic
                this.notificationTime.value = notificationTime
                this.notificationDays.value = currentUser?.notificationDays
            }
        } catch (t: Throwable) {
            Timber.i("TAG: ${t.message}")
        }
    }


    suspend fun loginUser(username: String, password: String) {
        val loginUser = LoginRequestModel(
            password = password,
            email = username
        )
        try {
            val loginUser = apiInteractor.logInUser(loginUser)//sending request to BE

            if (!loginUser?.token.isNullOrEmpty()) {
                dataStore.putAccessToken(loginUser?.token.toString())
            }

            accessToken.value = loginUser?.token

        } catch (t: Throwable) {
            Timber.i("TAG: ${t.message}")
        }
    }

    suspend fun forgotPassword(email: String) {
        val forgotPasswordRequest = ForgotPasswordRequest(
            email = email
        )
        try {
            apiInteractor.forgotPassword(forgotPasswordRequest)//sending request to BE
        } catch (t: Throwable) {
            Timber.i("TAG: ${t.message}")
        }
    }

    suspend fun updateUser(
        favoriteTopic: String,
        notificationDays: Array<String>,
        notificationTime: String
    ) {
        val updatingRequest = SetUserPreferencesRequestModel(
            favoriteTopic = favoriteTopic,
            notificationDays = notificationDays,
            notificationTime = notificationTime,
        )
        try {
            apiInteractor.updateUser(updatingRequest)
        } catch (t: Throwable) {

            Timber.i("TAG: ${t.message}")
        }
    }
}