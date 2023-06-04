package com.nistruct.meditation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.meditation.data.entity.MeditationModel
import com.nistruct.meditation.data.repo.MeditationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// provide the necessary data and functionality for the UI components to interact with

@HiltViewModel // enable injection of a ViewModel
class MeditationViewModel @Inject constructor(
    var meditationRepository: MeditationRepository
) : ViewModel() {//dependencies injected

    var meditations = MutableLiveData<Array<MeditationModel>>()

    init {
        meditations = meditationRepository.returnMeditationList()
        viewModelScope.launch {
            meditationRepository.getAllMeditations()
        }
    }



}