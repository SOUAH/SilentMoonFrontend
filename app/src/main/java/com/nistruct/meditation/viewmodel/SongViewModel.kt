package com.nistruct.meditation.viewmodel

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

// provide the necessary data and functionality for the UI components to interact with

@HiltViewModel // enable injection of a ViewModel
class SongViewModel @Inject constructor(
) : ViewModel() {//dependencies injected

    private var mediaPlayer: MediaPlayer? = null
    var isPlaying = MutableLiveData<Boolean>()
    var currentProgress = MutableLiveData<Int>()
    var totalDuration = MutableLiveData<Int>()

    init {
        isPlaying = MutableLiveData<Boolean>(false)
        currentProgress = MutableLiveData<Int>(0)
        totalDuration = MutableLiveData<Int>(0)

        prepareMediaPlayer()
    }

    private fun prepareMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
    }

    fun playAudioFromUrl(url: String) {
        mediaPlayer?.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            setOnPreparedListener {
                start()
                updateProgressAndDuration()
            }
            prepareAsync()
        }
    }

    fun startPlaying() {
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
            isPlaying.value = true
        }
    }

    fun pausePlaying() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            isPlaying.value = false
        }
    }

    fun updateProgressAndDuration() {
        totalDuration.value = mediaPlayer?.duration
        viewModelScope.launch {
            while (true) {
                currentProgress.value = mediaPlayer?.currentPosition ?: 0
                delay(1000)  // Update every second
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}