package com.nistruct.meditation.view.meditations

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nistruct.meditation.DesignContent.CircleButton
import com.nistruct.meditation.DesignContent.MainTitle
import com.nistruct.meditation.DesignContent.TitleDetail
import com.nistruct.meditation.R
import com.nistruct.meditation.ui.theme.Black
import com.nistruct.meditation.ui.theme.Clear_Yellow
import com.nistruct.meditation.ui.theme.Gray_level3
import com.nistruct.meditation.ui.theme.White
import com.nistruct.meditation.viewmodel.SongViewModel

@Composable
fun Music(navController: NavHostController, musicName: String) {
    val sliderValue = remember { mutableStateOf(0f) }

    val viewModel = hiltViewModel<SongViewModel>()

    val currentProgress by viewModel.currentProgress.observeAsState(initial = 0)
    val totalDuration by viewModel.totalDuration.observeAsState(initial = 0)

    LaunchedEffect(key1 = viewModel) {
        viewModel.playAudioFromUrl("https://7e6f-87-116-160-36.ngrok-free.app" + "/song.mp3")
    }

    val icPlayClicked = remember { mutableStateOf(true) }

    val icPlay =
        if (icPlayClicked.value) painterResource(id = R.drawable.stop_button) else painterResource(
            id = R.drawable.play_button
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Clear_Yellow)
    ) {
        Image(
            painter = painterResource(id = R.drawable.choose_topic), contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircleButton(
                    painterResource(id = R.drawable.back), White,
                    Black
                ) {
                    navController.popBackStack()
                }
            }
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            MainTitle(title = musicName, color = Black, align = TextAlign.Center)
            TitleDetail(text = "7 DAYS OF CALM", color = Gray_level3, align = TextAlign.Center)
            MusicIcons(icPlay, icPlayClicked, viewModel)
            SliderDesign(
                sliderValue = currentProgress.toFloat(),
                fullAudioLength = totalDuration.toFloat()
            )
        }
    }
}

@Composable
fun MusicIcons(
    ic_play: Painter,
    ic_play_clicked: MutableState<Boolean>,
    viewModel: SongViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Black.copy(alpha = 0.1f))
        ) {
            CircleButton(
                painterResource = ic_play,
                bg_color = Black,
                tint_color = White
            ) {
                if (ic_play_clicked.value) {
                    viewModel.pausePlaying()
                } else {
                    viewModel.startPlaying()
                }
                ic_play_clicked.value = !ic_play_clicked.value
            }
        }
    }
}

@Composable
fun SliderDesign(sliderValue: Float, fullAudioLength: Float) {
    val audioLengthMillis: Float = fullAudioLength
    val audioLengthMinutes = ((audioLengthMillis / 1000) / 60).toInt()
    val audioLengthSeconds = ((audioLengthMillis / 1000) % 60).toInt()
    val audioLength = String.format("%02d:%02d", audioLengthMinutes, audioLengthSeconds)

    val audioStartMillis: Float = sliderValue
    val audioStartMinutes = ((audioStartMillis / 1000) / 60).toInt()
    val audioStartSeconds = ((audioStartMillis / 1000) % 60).toInt()
    val audioStart = String.format("%02d:%02d", audioStartMinutes, audioStartSeconds)

    Slider(
        value = sliderValue,
        onValueChange = { /* Ignore user input as we control the slider from code */ },
        valueRange = 0f..audioLengthMillis,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = SliderDefaults.colors(
            thumbColor = Black,
            activeTrackColor = Black,
            inactiveTrackColor = Gray_level3
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = audioStart)
        Text(text = audioLength)
    }
}






