package com.nistruct.meditation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nistruct.meditation.DesignContent.*
import com.nistruct.meditation.R
import com.nistruct.meditation.ui.theme.*
import com.nistruct.meditation.viewmodel.UserViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Logout(navController: NavController) {
    var viewModel = hiltViewModel<UserViewModel>()

    Box(modifier = Modifier.fillMaxSize()) {
        Column() {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.auth),
                    contentDescription = null, modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.Crop
                )

                Column(Modifier.fillMaxSize()) {

                    CircleButton(
                        painterResource = painterResource(id = R.drawable.back),
                        bg_color = White,
                        tint_color = Black
                    ) {
                        navController.popBackStack()
                    }

                    MainTitle(
                        title = "Come Back!",
                        color = Black,
                        align = TextAlign.Center
                    )

                    ButtonDesign(
                        text_color = White,
                        bg_color = Purple,
                        text_title = "LOG OUT"
                    ) {
                        viewModel.logout()
                        navController.navigate("LaunchScreen")
                    }
                }
            }
        }
    }
}


