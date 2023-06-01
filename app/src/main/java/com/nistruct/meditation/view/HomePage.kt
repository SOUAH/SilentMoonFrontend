package com.nistruct.meditation.view.laterstage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nistruct.meditation.DesignContent.DailyCalm
import com.nistruct.meditation.view.Header
import com.nistruct.meditation.R
import com.nistruct.meditation.data.entity.SleepMode
import com.nistruct.meditation.ui.theme.*

@Composable
fun HomePage(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Header(
                CustomColor = Black,
                painterResource = painterResource(id = R.drawable.logo)
            )

            Text(
                text = "Good Morning, Avşar",
                textAlign = TextAlign.Start,
                fontSize = 28.sp,
                color = Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "We wish you have a good day!",
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                color = Gray_level3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 20.dp, top = 5.dp, bottom = 15.dp)
            )

            CardMode()

            DailyCalm(
                Black, painterResource(id = R.drawable.dark_daily_calm),
                painterResource(id = R.drawable.play_button), Black, White, White
            ) {
            }

            Text(
                text = "Recomended for you", color = Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 20.dp)
            )


            ModeArray(
                itemsTopics = listOf(
                    SleepMode("Focus", "MEDITAION • 3-10 MIN", R.drawable.focus),
                    SleepMode("Happines", "MEDITAION • 3-10 MIN", R.drawable.happiness),
                    SleepMode("Focus", "MEDITAION • 3-10 MIN", R.drawable.focus),
                    SleepMode("Happines", "MEDITAION • 3-10 MIN", R.drawable.happiness),
                    SleepMode("Focus", "MEDITAION • 3-10 MIN", R.drawable.focus),
                    SleepMode("Happines", "MEDITAION • 3-10 MIN", R.drawable.happiness),
                )
            )


        }
    }
}

@Composable
fun CardMode() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Box(
            modifier = Modifier
                .width(175.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(10))
                .background(Purple)
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.strawberry),
                    contentDescription = null,
                    alignment = Alignment.TopEnd,
                    modifier = Modifier.size(200.dp)
                )
            }
            Column(modifier = Modifier.padding(top = 50.dp)) {
                Text(
                    text = "Basics",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    color = Yellow,
                    modifier = Modifier
                        .fillMaxWidth().padding(15.dp),
                    fontWeight = FontWeight.Bold)
                Text(
                    text = "Course",
                    textAlign = TextAlign.Start,
                    fontSize = 11.sp,
                    color = Yellow,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "3-10 MIN")

                    Text(text = "START",
                        color = Black,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Gray_level1)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .clickable {

                            }

                    )
                }
            }


        }

        Box(
            modifier = Modifier

                .width(175.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(10))
                .background(Dark_Yellow)
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.relaxation),
                    contentDescription = null,
                    alignment = Alignment.TopEnd,
                    modifier = Modifier.size(200.dp)
                )
            }
            Column(modifier = Modifier.padding(top = 50.dp)) {
                Text(
                    text = "Relaxation",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    color = Yellow,
                    modifier = Modifier
                        .fillMaxWidth().padding(15.dp),
                    fontWeight = FontWeight.Bold)
                Text(
                    text = "Music",
                    textAlign = TextAlign.Start,
                    fontSize = 11.sp,
                    color = Yellow,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "3-10 MIN")

                    Text(text = "START",
                        color = Color.White,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Black)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .clickable {

                            }

                    )
                }
            }


        }

    }


}

@Composable
fun ModeArray(itemsTopics: List<SleepMode>) {

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        items(count = itemsTopics.size, itemContent = {
            SleepModeItem(
                item = itemsTopics[it],

                ) {

            }
        })


    }
}

@Composable
fun SleepModeItem(item: SleepMode, onItemClicked: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable {
            onItemClicked()
        }) {
        Image(
            painter = painterResource(id = item.icon_id),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 150.dp, height = 100.dp)

        )

        Text(
            text = item.title,
            fontWeight = FontWeight.Bold,
            color = Black,
            fontSize = 18.sp
        )
        Text(text = item.detail, fontSize = 11.sp, color = Gray_level3)

    }
}
