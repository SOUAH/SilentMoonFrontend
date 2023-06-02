package com.nistruct.meditation.view.meditations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.nistruct.meditation.DesignContent.BigTitleDesign
import com.nistruct.meditation.DesignContent.DailyCalm
import com.nistruct.meditation.DesignContent.TitleDetail
import com.nistruct.meditation.R
import com.nistruct.meditation.data.entity.MeditationModel
import com.nistruct.meditation.data.entity.TitleAndIconModel
import com.nistruct.meditation.ui.theme.*
import com.nistruct.meditation.view.BottomMenu
import com.nistruct.meditation.view.Header
import com.nistruct.meditation.viewmodel.MeditationViewModel


@Composable
fun MeditateV2(navController: NavHostController) {
    var viewModel: MeditationViewModel = hiltViewModel()
//    var nickNameDS = viewModel.getNickName()
    var selectedItemIndex = remember { mutableStateOf(0) }
    var meditations = viewModel.meditations.observeAsState()


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                MeditateBody(navController)
            }
            item {
                val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                FlowRow(
                    mainAxisSize = SizeMode.Expand,
                    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
                ) {
                }
            }
            item {
                meditations.value?.let { meditationList ->
                    Meditations(
                        meditations = meditationList, navController
                    )
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        BottomMenu(
            selectedItemIndex,
            items = listOf(
                TitleAndIconModel("Meditate", R.drawable.medidate),
            ),
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Meditations(meditations: Array<MeditationModel>, navController: NavHostController) {

    Column(modifier = Modifier.fillMaxWidth()) {


        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2), modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(meditations.size) { item ->
                MeditationItem(meditation = meditations[item], navController)
            }
        }

    }
}


@Composable
fun MeditationItem(meditation: MeditationModel, navController: NavHostController) {

    Box(
        modifier = Modifier
            .padding(7.5.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val painter = rememberImagePainter(meditation.imageUrl)
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate("CourseDetails/${meditation.name}")
                },
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
fun MeditateBody(navController: NavHostController) {
    Header(CustomColor = Black, painterResource = painterResource(id = R.drawable.logo))
    BigTitleDesign("Meditate", MaterialTheme.typography.h3, FontWeight.Bold)
    TitleDetail(
        text = "we can learn how to recognize when our minds are doing their normal everyday acrobatics.",
        color = Black,
        align = TextAlign.Center
    )

    MenuArray(
        itemsTopics = listOf(
            TitleAndIconModel("All", R.drawable.all),
            TitleAndIconModel("My", R.drawable.my),
            TitleAndIconModel("Kids", R.drawable.kids),
            TitleAndIconModel("Anxious", R.drawable.anxious),
        )
    )

    DailyCalm(
        Daily_Calm, painterResource(id = R.drawable.daily_calm),
        painterResource(id = R.drawable.play_button), White, Black, Black
    ) {

        navController.navigate("CourseDetails")

    }
}

@Composable
fun MenuArray(
    itemsTopics: List<TitleAndIconModel>,
    activeHighlightColor: Color = Purple,
    activeTextColor: Color = Black,
    inactiveTextColor: Color = White,
    initialSelectedItemIndex: Int = 0
) {

    var selectedItemIndex by remember { mutableStateOf(initialSelectedItemIndex) }

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 10.dp)
    ) {
        items(count = itemsTopics.size, itemContent = {
            MenuItem(
                item = itemsTopics[it],
                isSelected = (it == selectedItemIndex),
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = it
            }
        })

    }
}

@Composable
fun MenuItem(
    item: TitleAndIconModel,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Purple,
    activeTextColor: Color = White,
    inactiveTextColor: Color = Gray_level3,
    onItemClick: () -> Unit
) {

    Box(modifier = Modifier.padding(end = 5.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(25))
                    .clickable {
                        onItemClick()
                    },
                elevation = 5.dp
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) activeHighlightColor else inactiveTextColor)
                        .padding(10.dp)
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Icon(
                            painter = painterResource(id = item.icon_id),
                            contentDescription = item.title,
                            tint = if (isSelected) inactiveTextColor else activeTextColor,
                            modifier = Modifier.size(20.dp)
                        )

                    }
                }

            }
            Text(text = item.title, color = if (isSelected) activeTextColor else inactiveTextColor)
        }
    }
}



