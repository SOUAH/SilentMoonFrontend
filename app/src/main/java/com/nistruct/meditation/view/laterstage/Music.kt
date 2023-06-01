package com.nistruct.meditation.view.laterstage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.nistruct.meditation.DesignContent.MainTitle
import com.nistruct.meditation.R
import com.nistruct.meditation.data.entity.SleepMode
import com.nistruct.meditation.ui.theme.Black


@Composable
fun Music(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(Modifier.fillMaxSize()) {
            MainTitle(title = "Sleep Music", color = Black, align = TextAlign.Center)
            SleepMusicList(
                itemList = listOf(
                    SleepMode(
                        "Night Island",
                        "45 MIN • SLEEP MUSIC",
                        R.drawable.night_island
                    ),
                    SleepMode("Sweet Sleep", "45 MIN • SLEEP MUSIC", R.drawable.sweet_sleep_owls),
                    SleepMode("Good Night", "45 MIN • SLEEP MUSIC", R.drawable.good_night),
                    SleepMode(
                        "Night Island",
                        "45 MIN • SLEEP MUSIC",
                        R.drawable.night_island
                    ),
                    SleepMode("Sweet Sleep", "45 MIN • SLEEP MUSIC", R.drawable.sweet_sleep_owls),
                    SleepMode("Good Night", "45 MIN • SLEEP MUSIC", R.drawable.good_night),
                    SleepMode(
                        "Night Island",
                        "45 MIN • SLEEP MUSIC",
                        R.drawable.night_island
                    ),
                    SleepMode("Sweet Sleep", "45 MIN • SLEEP MUSIC", R.drawable.sweet_sleep_owls),
                    SleepMode("Good Night", "45 MIN • SLEEP MUSIC", R.drawable.good_night),
                    SleepMode(
                        "Night Island",
                        "45 MIN • SLEEP MUSIC",
                        R.drawable.night_island
                    ),
                    SleepMode("Sweet Sleep", "45 MIN • SLEEP MUSIC", R.drawable.sweet_sleep_owls),
                    SleepMode("Good Night", "45 MIN • SLEEP MUSIC", R.drawable.good_night),
                )
            )
        }
    }
}