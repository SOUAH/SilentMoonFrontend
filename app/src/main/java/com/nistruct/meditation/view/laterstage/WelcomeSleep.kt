package com.nistruct.meditation.view.laterstage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nistruct.meditation.DesignContent.ButtonDesign
import com.nistruct.meditation.DesignContent.MainTitle
import com.nistruct.meditation.DesignContent.TitleDetail
import com.nistruct.meditation.R
import com.nistruct.meditation.ui.theme.Rename_Blue
import com.nistruct.meditation.ui.theme.Purple
import com.nistruct.meditation.ui.theme.White

@Composable
fun WelcomeSleep(navController: NavHostController) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Rename_Blue)
    ) {

        Image(
            painter = painterResource(id = R.drawable.welcome_screen),
            contentDescription = null, modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.welcome_logo_2birds),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 44.dp)
        )

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
    
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)) {
                MainTitle(title = "Welcome to sleep", color = White, align = TextAlign.Center)

                TitleDetail(
                    text = "Explore the new king of sleep. It uses sound\n" +
                            "and vesualization to create perfect conditions for refreshing sleep.",
                    color = White,
                    align = TextAlign.Center
                )
            }

            ButtonDesign(text_color = White, bg_color = Purple, text_title = "GET STARTED") {

            }


        }
    }

}