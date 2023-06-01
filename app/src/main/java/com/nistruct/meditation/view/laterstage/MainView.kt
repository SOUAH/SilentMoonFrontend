package com.nistruct.meditation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nistruct.meditation.R
import com.nistruct.meditation.data.entity.TitleAndIconModel
import com.nistruct.meditation.ui.theme.Gray
import com.nistruct.meditation.ui.theme.Purple

@Composable
fun MainViewLaterStage(navController: NavHostController) {
    var selectedItemIndex = remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(bottom = 90.dp)) {
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            BottomMenu(selectedItemIndex,

                    items = listOf(
                    TitleAndIconModel("Home", R.drawable.home),
                    TitleAndIconModel("Sleep", R.drawable.sleep),
                    TitleAndIconModel("Meditate", R.drawable.medidate),
                    TitleAndIconModel("Music", R.drawable.music),
                    TitleAndIconModel("Profile", R.drawable.user)
                ),

            )
        }
    }
}

@Composable
fun BottomMenu(
    selectedItemIndex: MutableState<Int>,
    items: List<TitleAndIconModel>,
    activeHighlightColor: Color = Purple,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color =  Gray,
//    initialSelectedItemIndex: Int = 0

) {

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(15.dp)
    ) {

        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = (index == selectedItemIndex.value),
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex.value = index
            }

        }

    }


}

@Composable
fun BottomMenuItem(
    item: TitleAndIconModel,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Purple,
    activeTextColor: Color = Purple,
    inactiveTextColor: Color = Gray,
    onItemClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {

                        onItemClick()
                    }
                )
            }

    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Icon(
                    painter = painterResource(id = item.icon_id), contentDescription = item.title,
                    tint = if (isSelected) activeTextColor else inactiveTextColor,
                    modifier = Modifier.size(20.dp)
                )


            }
        }

        Text(text = item.title, color = if (isSelected) Purple else Gray)

    }
}