package com.nistruct.meditation.viewmodel

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistruct.meditation.data.entity.TopicModel
import com.nistruct.meditation.data.repo.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// provide the necessary data and functionality for the UI components to interact with

@HiltViewModel // enable injection of a ViewModel
class SongViewModel @Inject constructor(
) : ViewModel() {//dependencies injected

    private var mediaPlayer: MediaPlayer? = null
    var isPlaying = MutableLiveData<Boolean>()

    init {
        isPlaying = MutableLiveData<Boolean>(false)
        prepareMediaPlayer()
    }

    private fun prepareMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )

//            setOnPreparedListener {
//                // MediaPlayer is prepared and can be started here
//                // Or you can use it to update UI
//            }
//
//            setOnCompletionListener {
//                // MediaPlayer has completed playing media
//                // You can update your UI here to reflect that the media is finished playing
//            }
        }
    }

    fun playAudioFromUrl(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }

    fun startPlaying() {
        mediaPlayer?.start()
        isPlaying.value = true
    }

    fun pausePlaying() {
        mediaPlayer?.pause()
        isPlaying.value = false
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}