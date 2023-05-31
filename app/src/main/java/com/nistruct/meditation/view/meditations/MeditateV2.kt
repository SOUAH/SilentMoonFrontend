package com.nistruct.meditation.view.meditations


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
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
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.nistruct.meditation.DesignContent.BigTitleDesign
import com.nistruct.meditation.DesignContent.DailyCalm
import com.nistruct.meditation.DesignContent.TitleDetail
import com.nistruct.meditation.R
import com.nistruct.meditation.data.entity.TitleAndIconModel
import com.nistruct.meditation.ui.theme.*
import com.nistruct.meditation.view.BottomMenu
import com.nistruct.meditation.view.Header
import com.nistruct.meditation.viewmodel.UserViewModel


@Composable
fun MeditateV2(navController: NavHostController) {
    var viewModel: UserViewModel = hiltViewModel()
//    var nickNameDS = viewModel.getNickName()
    var selectedItemIndex = remember { mutableStateOf(0) }


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
                    SeasonsSection(
                        item = listOf(
                            TitleAndIconModel("Summer", R.drawable.summer),
                            TitleAndIconModel("Spring", R.drawable.spring),
                            TitleAndIconModel("Winter", R.drawable.winter_trees),
                            TitleAndIconModel("Winter", R.drawable.winter_trees),
                            TitleAndIconModel("Winter", R.drawable.winter_trees),
                            TitleAndIconModel("Winter", R.drawable.winter_trees),
                            TitleAndIconModel("Winter", R.drawable.winter_trees),
                            TitleAndIconModel("Winter", R.drawable.winter_trees),
                            TitleAndIconModel("Winter", R.drawable.winter_trees),
                            TitleAndIconModel("Winter", R.drawable.winter_trees),
                            TitleAndIconModel("Winter", R.drawable.winter_trees),
                            TitleAndIconModel("Autumn", R.drawable.autumn)
                        )
                    )
                }


            }
        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            BottomMenu(
                selectedItemIndex,

                items = listOf(
                    TitleAndIconModel("Sleep", R.drawable.sleep),
                    TitleAndIconModel("Meditate", R.drawable.medidate),
//                    TitleAndIconModel("$nickNameDS", R.drawable.user)
                ),

                )
        }


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

    DailyCalm(
        Daily_Calm, painterResource(id = R.drawable.daily_calm),
        painterResource(id = R.drawable.play_button), White, Black, Black
    ) {

        navController.navigate("CourseDetails")

    }
}

@Composable
fun SeasonsSection(item: List<TitleAndIconModel>) {
    val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
    ) {

        item.forEachIndexed { index, item ->
            SeasonItem(item_season = item, itemSize)
        }
    }
}

@Composable
fun SeasonItem(item_season: TitleAndIconModel, itemSize: Dp) {

    Box(
        modifier = Modifier
            .width(itemSize)
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
    ) {

        Image(
            painter = painterResource(id = item_season.icon_id),
            contentDescription = item_season.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .blur(100.dp)
        ) {
            Text(
                text = item_season.title,
                textAlign = TextAlign.Start,
                color = White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }
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



