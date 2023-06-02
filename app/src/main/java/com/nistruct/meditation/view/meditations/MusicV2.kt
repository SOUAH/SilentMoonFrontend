package com.nistruct.meditation.view.meditations

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nistruct.meditation.DesignContent.CircleButton
import com.nistruct.meditation.DesignContent.MainTitle
import com.nistruct.meditation.DesignContent.TitleDetail
import com.nistruct.meditation.R
import com.nistruct.meditation.ui.theme.*
import com.nistruct.meditation.viewmodel.SongViewModel
import com.nistruct.meditation.viewmodel.UserViewModel

@Composable
fun MusicV2(navController: NavHostController, musicName: String) {
    val ic_hearth_clicked = remember { mutableStateOf(false) }
    val ic_hearth = if (ic_hearth_clicked.value) painterResource(id = R.drawable.red_heart)
    else painterResource(id = R.drawable.heart)

    val sliderValue = remember { mutableStateOf(0f) }

    val audio_lenght = 180000f
    val second15 = 15000f

    val viewModel= hiltViewModel<SongViewModel>()
    viewModel.playAudioFromUrl("https://7b56-41-62-117-63.eu.ngrok.io/song.mp3")

    var isPlaying = viewModel.isPlaying.observeAsState(initial = false)

    val ic_play = if (isPlaying.value) painterResource(id = R.drawable.stop_button) else painterResource(id = R.drawable.play_button)

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

                Row() {
                    CircleButton(
                        ic_hearth, Black,
                        White
                    ) {
                        ic_hearth_clicked.value = !ic_hearth_clicked.value
                        navController.popBackStack()
                    }
                    CircleButton(
                        painterResource(id = R.drawable.download), Black,
                        White
                    ) {
                        navController.popBackStack()
                    }
                }

            }

        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

            MainTitle(title = musicName, color = Black, align = TextAlign.Center)

            TitleDetail(text = "7 DAYS OF CALM", color = Gray_level3, align = TextAlign.Center)

            MusicIcons(ic_play, isPlaying,sliderValue,audio_lenght,second15)

            SliderDesign(sliderValue,audio_lenght)

        }

        if (!isPlaying.value) {
            viewModel.pausePlaying()
        } else {
            viewModel.startPlaying()
        }

    }
}
@Composable
fun MusicIcons(
    ic_play: Painter,
    ic_play_clicked: State<Boolean>,
    sliderValue: MutableState<Float>,
    audio_lenght: Float,
    second15: Float,
 ) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp), horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,

    ) {

        Icon(
            painter = painterResource(id = R.drawable.past_15_sc),
            contentDescription = "",
            tint = Gray_level3,
            modifier = Modifier
                .size(30.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            if (sliderValue.value >= second15) sliderValue.value -= second15
                        }
                    )
                }
        )
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
//                ic_play_clicked.value = !ic_play_clicked.value
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.next_15_sc),
            contentDescription = "",
            tint = Gray_level3,
            modifier = Modifier
                .size(30.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            if (sliderValue.value + second15 <= audio_lenght) sliderValue.value += second15
                        }
                    )
                }
        )
    }
}

@Composable
fun SliderDesign(sliderValue: MutableState<Float>, audio_lenght: Float) {

    val audio_lenght_millis : Float =audio_lenght.toFloat()
    val audio_lenght_minutes  = ((audio_lenght_millis / 1000)  / 60).toInt()
    val audio_lenght_seconds = ((audio_lenght_millis / 1000) % 60).toInt()
    val audio_lenght = "$audio_lenght_minutes:$audio_lenght_seconds"

    val audio_start_millis : Float = sliderValue.value
    val audio_start_minutes  = ((audio_start_millis / 1000)  / 60).toInt()
    val audio_start_seconds = ((audio_start_millis / 1000) % 60).toInt()
    val audio_start = "$audio_start_minutes:$audio_start_seconds"



    Slider(value = sliderValue.value, onValueChange = {
        sliderValue.value = it

    }, valueRange = 0f..audio_lenght_millis,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = SliderDefaults.colors(
            thumbColor = Black,
            activeTrackColor = Black,
            inactiveTrackColor = Gray_level3
        ))

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = audio_start)
        Text(text = audio_lenght)

    }
}






