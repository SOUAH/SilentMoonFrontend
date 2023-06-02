package com.nistruct.meditation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.meditation.data.AppDataStore.DataStoreInterface
import com.nistruct.meditation.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// provide the necessary data and functionality for the UI components to interact with

@HiltViewModel // enable injection of a ViewModel
class UserViewModel @Inject constructor(
    var userRepository: UserRepository,
) : ViewModel() {//dependencies injected

    var email = MutableLiveData<String>()
    var username = MutableLiveData<String>()
    var accessToken = MutableLiveData<String>()

    var favTopic = MutableLiveData<String>()
    var notificationTime = MutableLiveData<String>()
    var notificationDays = MutableLiveData<Array<String>>()

    init {
        email = userRepository.returnEmail()
        username = userRepository.returnUsername()
        accessToken = userRepository.returnToken()

        var favTopic = userRepository.returnFavTopic()
        var notificationTime = userRepository.returnNotificationTime()
        var notificationDays = userRepository.returnNotificationDays()
    }


    fun signUp(email: String, username: String, password: String) = viewModelScope.launch {
        userRepository.registerUser(email, username, password)
        userRepository.getCurrentUser()
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            userRepository.loginUser(email, password)
            userRepository.getCurrentUser()
        }
    }

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            userRepository.forgotPassword(email)
        }
    }

    fun updatUser(
        favoriteTopic: String,
        notificationDays: Array<String>,
        notificationTime: String
    ) {
        viewModelScope.launch {
            userRepository.updateUser(favoriteTopic, notificationDays, notificationTime)
        }
    }
}