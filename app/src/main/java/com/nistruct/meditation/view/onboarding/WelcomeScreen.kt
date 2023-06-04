package com.nistruct.meditation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nistruct.meditation.DesignContent.ButtonDesign
import com.nistruct.meditation.R
import com.nistruct.meditation.ui.theme.Black
import com.nistruct.meditation.ui.theme.Purple
import com.nistruct.meditation.ui.theme.White
import com.nistruct.meditation.ui.theme.Yellow
import com.nistruct.meditation.viewmodel.UserViewModel

@Composable
fun WelcomeScreen(navController: NavHostController, getNickname: String) {

    var viewModel = hiltViewModel<UserViewModel>()
    var favTopic = viewModel.favTopic.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple)
        ) {

            Header(White, painterResource(id = R.drawable.logo))

            TextWelcomeTitle(getNickname)

            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.welcome),
                    contentDescription = null, modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.padding(40.dp))

            ButtonDesign(
                text_color = Black,
                bg_color = White,
                text_title = "GET STARTED"
            ) {
                if (!favTopic.value.isNullOrEmpty()) {
                    navController.navigate("Meditate")
                } else {
                    navController.navigate("ChooseTopic")
                }
            }
        }
    }
}

@Composable
fun TextWelcomeTitle(getNickname: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hi $getNickname, Welcome" +
                    "\nto the silent Moon",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Yellow,
            lineHeight = 41.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Explore the app, Find some peace of mind to prepare for meditation.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Thin,
            color = White,
            textAlign = TextAlign.Center
        )
    }
}


