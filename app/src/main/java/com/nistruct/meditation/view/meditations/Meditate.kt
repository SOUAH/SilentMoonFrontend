package com.nistruct.meditation.view.meditations

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.nistruct.meditation.DesignContent.BottomMenu
import com.nistruct.meditation.DesignContent.DailyCalm
import com.nistruct.meditation.DesignContent.TitleDetail
import com.nistruct.meditation.R
import com.nistruct.meditation.data.entity.MeditationModel
import com.nistruct.meditation.data.entity.TitleAndIconModel
import com.nistruct.meditation.ui.theme.*
import com.nistruct.meditation.view.Header
import com.nistruct.meditation.viewmodel.MeditationViewModel


@Composable
fun Meditate(navController: NavHostController) {
    var viewModel: MeditationViewModel = hiltViewModel()
    var selectedItemIndex = remember { mutableStateOf(0) }
    var meditations = viewModel.meditations.observeAsState()
    Scaffold(
        bottomBar = {
            BottomMenu(
                selectedItemIndex = selectedItemIndex,
                items = listOf(
                    TitleAndIconModel("Meditate", R.drawable.medidate),
                    TitleAndIconModel("Account", R.drawable.user),
                ),
                navController = navController
            )
        }
    )

    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MeditateBody(navController)
            val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
            FlowRow(
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
            ) {
            }
            meditations.value?.let { meditationList ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(meditationList.size) { index ->
                        MeditationItem(meditation = meditationList[index], navController)
                    }
                }
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
                    navController.navigate("CourseDetails/${meditation.id}")
                },
            contentScale = ContentScale.FillBounds,
        )
    }
}


@Composable
fun MeditateBody(navController: NavHostController) {
    Header(CustomColor = Black, painterResource = painterResource(id = R.drawable.logo))
    BigTitleDesign("Meditate", MaterialTheme.typography.h4, FontWeight.Bold)
    TitleDetail(
        text = "we can learn how to recognize when our minds are doing their normal everyday acrobatics.",
        color = Gray,
        align = TextAlign.Center
    )
    MenuArray(
        itemsTopics = listOf(
            TitleAndIconModel("All", R.drawable.all),
            TitleAndIconModel("My", R.drawable.my),
            TitleAndIconModel("Kids", R.drawable.kids),
            TitleAndIconModel("Anxious", R.drawable.anxious),
            TitleAndIconModel("All", R.drawable.all),
            TitleAndIconModel("My", R.drawable.my),
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
            Text(text = item.title, color = Black)
        }
    }
}



