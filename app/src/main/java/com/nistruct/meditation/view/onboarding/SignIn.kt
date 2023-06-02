package com.nistruct.meditation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nistruct.meditation.DesignContent.*
import com.nistruct.meditation.R
import com.nistruct.meditation.ui.theme.*
import com.nistruct.meditation.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignIn(navController: NavController) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    val emailSignIn = remember { mutableStateOf("") }
    val passwordSignIn = remember { mutableStateOf("") }
    var showPassword = remember {
        mutableStateOf(false)
    }
    var iconPassword = if (showPassword.value)
        painterResource(id = R.drawable.show_password_eye)
    else painterResource(id = R.drawable.show_password_eye)

    var viewModel = hiltViewModel<UserViewModel>()

    var tokenResponse = viewModel.accessToken.observeAsState()
    var username = viewModel.username.observeAsState()
    var btnClicked = remember { mutableStateOf(false) }


    Scaffold(scaffoldState = scaffoldState, content = {//for showing error msg down in the screen
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
                            title = "Welcome Back!",
                            color = Black,
                            align = TextAlign.Center
                        )

                        TextFieldDesign(
                            value = emailSignIn,
                            hint = "Email adress",
                            painterResource = painterResource(id = R.drawable.check),
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ) {}

                        TextFieldPasswordDesign(
                            value = passwordSignIn,
                            hint = "Password",
                            painterResource = iconPassword,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done, showPassword
                        )

                        ButtonDesign(
                            text_color = White,
                            bg_color = Purple,
                            text_title = "LOG IN"
                        ) {
                            //when user clicks on login button, this is what happens behind the button
                            if (emailSignIn.value != "" && passwordSignIn.value != "") {
                                viewModel.signIn(emailSignIn.value, passwordSignIn.value)
                                btnClicked.value = !btnClicked.value
                            } else {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(message = "Fill the blanks!")
                                }
                            }
                        }

                        Text(
                            text = "Forgot Password?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .clickable() {
                                    if (isValidEmail(emailSignIn.value)) {
                                        viewModel.forgotPassword(emailSignIn.value)
                                        scope.launch {
                                            scaffoldState.snackbarHostState.showSnackbar(message = "Reset password sent!")
                                        }
                                    } else {
                                        scope.launch {
                                            scaffoldState.snackbarHostState.showSnackbar(message = "Email field is empty!")
                                        }
                                    }
                                }
                        )
                    }
                }

            }
        }
        if (btnClicked.value) {
            if (tokenResponse.value.toString() !== "" && username.value.toString() != "") {
                navController.navigate("WelcomeScreen/${username.value.toString()}")
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(message = "Success Log In!")
                }
                btnClicked.value = !btnClicked.value
            }
        }

        fun isValidEmail(email: String): Boolean {
            val regexPattern = Regex("^([A-Za-z0-9_+\\-.])+@([A-Za-z0-9_+\\-.])+\\.([A-Za-z]{2,})$")
            return regexPattern.matches(email)
        }
    })


}


