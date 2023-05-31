package com.nistruct.meditation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nistruct.meditation.DesignContent.ButtonDesign
import com.nistruct.meditation.DesignContent.MainTitle
import com.nistruct.meditation.DesignContent.TitleDetail
import  com.nistruct.meditation.R
import com.nistruct.meditation.ui.theme.*


@Composable
fun SignUpAndSignIn(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.frame), contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.FillBounds
                )
                Column {

                    Header(Black, painterResource(id = R.drawable.logo))
                    Image(
                        painter = painterResource(id = R.drawable.onboard_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(242.dp)
                            .padding(40.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            MainTitle("We are what we do", Black, TextAlign.Center)
            TitleDetail("Thousand of people are using silent moon \n for smalls meditation",
                Gray,
                TextAlign.Center)

            ButtonDesign(White, Purple, "SIGN UP") {
                navController.navigate("signUp")
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "ALREADY HAVE AN ACCOUNT?",
                    fontSize = 14.sp, color = Gray)
                LogInButton("LOG IN") {
                    navController.navigate("SignIn")
                }
            }
        }
    }
}


@Composable
fun LogInButton(title: String, onItemClick: () -> Unit) {
    Text(
        text = title,
        fontSize = 14.sp,
        color = Purple,
        modifier = Modifier
            .padding(start = 4.dp)
            .clickable {
                onItemClick()
            }

    )
}


@Composable
fun Header(CustomColor: Color, painterResource: Painter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Silent",
            color = CustomColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Image(
            painter = painterResource,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)

        )
        Text(
            text = "Moon",
            color = CustomColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold

        )

    }

}
