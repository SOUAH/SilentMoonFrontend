package com.nistruct.meditation.view.meditations


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.nistruct.meditation.DesignContent.CircleButton
import kotlin.math.max
import kotlin.math.min
import com.nistruct.meditation.R
import com.nistruct.meditation.data.entity.MusicModel
import com.nistruct.meditation.ui.theme.*

val AppBarCollapsedHeight = 70.dp
val AppBarExpendedHeight = 400.dp

@Composable
fun CourseDetails(navController: NavHostController) {

    ProvideWindowInsets {
        Surface(color = White) {
            MainFragment(navController)
        }
    }
}

@Composable
fun MainFragment(navController: NavHostController) {
    val scrollState = rememberLazyListState()


    Box {
        ToolbarDesign(scrollState, navController)
        Content(scrollState, navController)

    }
}


@Composable
fun ToolbarDesign(scrollState: LazyListState, navController: NavHostController) {
    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    val icon_clicked = remember { mutableStateOf(false) }

    val icon = if (icon_clicked.value) painterResource(id = R.drawable.red_heart)
    else painterResource(id = R.drawable.heart)

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = White,
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp

    ) {
        Column {
            Box(
                Modifier
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetProgress
                    }) {
                Image(
                    painter = painterResource(id = R.drawable.sun),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Transparent),
                                    Pair(1f, White)
                                )
                            )
                        )
                )

            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapsedHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Happy Morning",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = (16 + 50 * offsetProgress).dp)
                        .scale(1f - 0.25f * offsetProgress)
                )

            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapsedHeight)
            .padding(horizontal = 16.dp)
    ) {

        CircleButton(
            painterResource = painterResource(id = R.drawable.back),
            bg_color = com.nistruct.meditation.ui.theme.White,
            tint_color = Black
        ) {
            navController.popBackStack()
        }
        Row() {
            CircleButton(
                painterResource = icon,
                bg_color = Black,
                tint_color = com.nistruct.meditation.ui.theme.White
            ) {
                icon_clicked.value = !icon_clicked.value

            }
            CircleButton(
                painterResource = painterResource(id = R.drawable.download),
                bg_color = Black,
                tint_color = com.nistruct.meditation.ui.theme.White
            ) {

            }
        }
    }
}

@Composable
fun Content(scrollState: LazyListState, navController: NavHostController) {
    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState) {
        item {

            CourseDetailsBody()
            Divider()

            Column {
                ListenMusics(
                    itemMusic = listOf(
                        MusicModel("Focus Attention", 3),
                        MusicModel("Body Scan", 5),
                        MusicModel("Making Happiness", 3),
                        MusicModel("Body Scan", 5),
                        MusicModel("Focus Attention", 3),
                        MusicModel("Focus Attention", 3),
                        MusicModel("Focus Attention", 3),
                        MusicModel("Focus Attention", 3),
                        MusicModel("Focus Attention", 3),
                        MusicModel("Focus Attention", 3),
                        MusicModel("Focus Attention", 3),
                        MusicModel("Body Scan", 5)
                    ), navController

                )
            }


        }
    }
}

@Composable
fun CourseDetailsBody() {
    Text(
        text = "COURSE", color = Gray_level3, textAlign = TextAlign.Start,
        modifier = Modifier.padding(20.dp)
    )
    Text(
        text = "Ease the mind into a restful night’s sleep  with \n" + "these deep, ambient tones.",
        color = Gray_level3,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(start = 20.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.red_heart),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(
                    20.dp
                )
            )
            Text(
                text = "24.243 Favorists",
                color = Gray_level3,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.head_phone),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "34.234 Listening",
                color = Gray_level3,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

    }
}

@Composable
fun ListenMusics(
    itemMusic: List<MusicModel>,
    navController: NavHostController,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember { mutableStateOf(initialSelectedItemIndex) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        itemMusic.forEachIndexed { index, item ->

            MusicItem(
                item = item,
                isSelected = (index == selectedItemIndex), navController
            ) {
                selectedItemIndex = index

                navController.navigate("MusicV2/${item.title}")

            }
        }

    }
}

@Composable
fun MusicItem(
    item: MusicModel,
    isSelected: Boolean,
    navController: NavHostController,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(60.dp),
                border = BorderStroke(
                    1.dp, if (isSelected) Purple
                    else Gray_level3
                ),
                shape = CircleShape,
                elevation = 7.dp
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            onItemClick()

                        }
                        .background(if (isSelected) Purple else com.nistruct.meditation.ui.theme.White),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.play_button),
                        contentDescription = "",
                        tint = if (isSelected) com.nistruct.meditation.ui.theme.White
                        else Gray_level3, modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                    )
                }

            }

            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h5,
                    color = Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${item.time} MIN",
                    color = Black.copy(alpha = 0.6f)
                )
            }


        }

        Divider()
    }
}

