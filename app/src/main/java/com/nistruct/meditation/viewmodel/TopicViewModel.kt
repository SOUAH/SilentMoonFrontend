package com.nistruct.meditation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.meditation.data.entity.TopicModel
import com.nistruct.meditation.data.repo.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// provide the necessary data and functionality for the UI components to interact with
@HiltViewModel // enable injection of a ViewModel
class TopicViewModel @Inject constructor(
    var topicRepository: TopicRepository
) : ViewModel() {//dependencies injected

    var topics = MutableLiveData<Array<TopicModel>>()

    init {
        topics = topicRepository.returntopicList()
        viewModelScope.launch {
            topicRepository.getAllTopics()
        }
    }

}