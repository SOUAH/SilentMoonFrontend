package com.nistruct.meditation.DesignContent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nistruct.meditation.data.entity.TitleAndIconModel
import com.nistruct.meditation.ui.theme.*


@Composable
fun ButtonDesign(
    text_color: Color,
    bg_color: Color,
    text_title: String,
    onItemClicked: () -> Unit
) {
    Text(
        text = text_title,
        color = text_color,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip((RoundedCornerShape(50)))
            .background(bg_color)
            .clickable {
                onItemClicked()
            }
            .padding(20.dp))
}
@Composable
fun BigTitleDesign(text: String, h3: TextStyle, bold: FontWeight) {
    Text(
        text = text,
        style = h3,
        fontWeight = bold)
}

@Composable
fun MainTitle(title: String, color: Color, align: TextAlign) {
    Text(
        text = title,
        textAlign = align,
        fontSize = 30.sp, color = color,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TitleDetail(text: String, color: Color, align: TextAlign) {
    Text(
        text = text,
        textAlign = align,
        fontSize = 16.sp,
        color = color,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
    )
}

@Composable
fun CircleButton(
    painterResource: Painter,
    bg_color: Color,
    tint_color: Color,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(70.dp)
            .padding(all = 10.dp),
        shape = CircleShape,
        elevation = 7.dp,
        border = BorderStroke(1.dp, tint_color.copy(alpha = 0.4f))
    ) {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    onItemClicked()
                }
                .background(bg_color)
             ) {
            Image(
                painter = painterResource,
                contentDescription = "", modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            )
        }
    }
}

@Composable
fun TextFieldDesign(
    value: MutableState<String>,
    hint: String,
    painterResource: Painter,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onItemClicked: () -> Unit

) {
    OutlinedTextField(
        value = value.value,
        onValueChange = {
            value.value = it
        },
        placeholder = { Text(hint, color = Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Gray_level2),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Gray,
            unfocusedBorderColor = Gray,
            cursorColor = Black
        ),
        trailingIcon = {
            IconButton(onClick = {
                onItemClicked()
            }) {
                Icon(
                    painter = painterResource,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    )
}

@Composable
fun TextFieldPasswordDesign(
    value: MutableState<String>,
    hint: String,
    painterResource: Painter,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    showPassword: MutableState<Boolean>

) {
    OutlinedTextField(
        value = value.value,
        onValueChange = {
            value.value = it
        },
        placeholder = { Text(hint, color = Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Gray_level2),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Gray,
            unfocusedBorderColor = Gray,
            cursorColor = Black
        ),
        trailingIcon = {
            IconButton(onClick = {
                showPassword.value = !showPassword.value
            }) {
                Icon(
                    painter = painterResource,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        },
        visualTransformation = if (showPassword.value) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Composable
fun DailyCalm(
    bg_color: Color,
    bg_image: Painter,
    painterResource: Painter,
    ic_tint: Color,
    circle_color: Color,
    title_color : Color,
    onItemClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(20.dp)
            .clip(RoundedCornerShape(20))
            .background(bg_color)

    ) {
        Image(
            painter = bg_image, contentDescription = null,
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(
                    text = "Daily Calm",
                    style = MaterialTheme.typography.h5,
                    color = title_color,
                    fontWeight = FontWeight.Bold
                )
            }
            Card(
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                elevation = 3.dp) {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            onItemClicked()
                        }
                        .background(circle_color)
                ) {
                    Icon(
                        painter = painterResource,
                        contentDescription = "",
                        tint = ic_tint, modifier = Modifier
                            .fillMaxSize()
                            .padding(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PlayIconsDesign(
    painterResource: Painter, bg_color: Color, ic_tint: Color,
    onItemClicked: () -> Unit
) {

    Card(
        modifier = Modifier
            .size(60.dp),
        shape = CircleShape,
        elevation = 7.dp,

        ) {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    onItemClicked()
                }
                .background(bg_color)
        ) {
            Icon(
                painter = painterResource,
                contentDescription = "",
                tint = ic_tint, modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
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
    inactiveTextColor: Color = Gray,
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


