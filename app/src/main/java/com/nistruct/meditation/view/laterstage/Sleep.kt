package com.nistruct.meditation.view.laterstage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nistruct.meditation.R
import com.nistruct.meditation.data.entity.SleepMode
import com.nistruct.meditation.data.entity.TitleAndIconModel
import com.nistruct.meditation.ui.theme.*
import com.nistruct.meditation.view.meditations.MenuArray

@Composable
fun Sleep(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Rename_Blue)
    ) {

        Box() {
            Image(
                painter = painterResource(id = R.drawable.sleep1), contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.moons),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Sleep Stories",
                    fontSize = 28.sp,
                    color = White_level1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Soothing bedtime stories to help you fall\n" +
                            "into a deep and natural sleep",
                    fontSize = 16.sp,
                    color = Gray_level1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )

                MenuArray(
                    itemsTopics = listOf(
                        TitleAndIconModel("All", R.drawable.all),
                        TitleAndIconModel("Sleep", R.drawable.sleep),
                        TitleAndIconModel("My", R.drawable.my),
                        TitleAndIconModel("Kids", R.drawable.kids),
                        TitleAndIconModel("Anxious", R.drawable.anxious),
                        TitleAndIconModel("My", R.drawable.my),
                        TitleAndIconModel("Anxious", R.drawable.anxious),
                        TitleAndIconModel("Sleep", R.drawable.sleep),
                        TitleAndIconModel("Kids", R.drawable.kids),
                    )
                )


                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                        .clip(RoundedCornerShape(15))
                        .padding(15.dp),

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_sleep),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "The ocean moon",
                            fontSize = 28.sp,
                            color = Yellow,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 40.dp)
                        )

                        Text(
                            text = "Non-stop 8- hour mixes of our \n most popular sleep audio",
                            fontSize = 16.sp,
                            color = Gray_level1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 20.dp)
                        )

                        Text(text = "START",
                            color = Black,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Gray_level1)
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                                .clickable {

                                })


                    }

                }

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

}

@Composable
fun SleepMusicList(itemList: List<SleepMode>) {
    Column(modifier = Modifier.fillMaxWidth()) {

        val state = rememberLazyGridState()
        LazyVerticalGrid(
            state = state,
            columns = GridCells.Fixed(2), modifier = Modifier.padding(end = 8.dp)
        ) {
            items(itemList.size) {
                SleepMusicItem(item = itemList[it]) {

                }
            }
        }
    }
}

@Composable
fun SleepMusicItem(item: SleepMode, onItemClicked: () -> Unit) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp)
            .aspectRatio(1f)
    ) {
        Column(modifier = Modifier
            .clickable {
                onItemClicked()
            }) {
            Image(
                painter = painterResource(id = item.icon_id),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth())

            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                color = White,
                fontSize = 18.sp
            )
            Text(text = item.detail, fontSize = 11.sp, color = Gray_level3)

        }
    }
}



