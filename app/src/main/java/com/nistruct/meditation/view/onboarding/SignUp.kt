package com.nistruct.meditation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nistruct.meditation.DesignContent.*
import com.nistruct.meditation.R
import com.nistruct.meditation.ui.theme.Black
import com.nistruct.meditation.ui.theme.Gray
import com.nistruct.meditation.ui.theme.Purple
import com.nistruct.meditation.ui.theme.White
import com.nistruct.meditation.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUp(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val emailSignUp = remember { mutableStateOf("") }
    val usernameSignUp = remember { mutableStateOf("") }
    val passwordSignUp = remember { mutableStateOf("") }
    val checkBoxPolicy = remember { mutableStateOf(false) }
    var viewModel: UserViewModel = hiltViewModel()
    var emailResponse = viewModel.email.observeAsState()
    var username = viewModel.username.observeAsState()
    var tokenResponse = viewModel.accessToken.observeAsState()

    var buttonClicked = remember { mutableStateOf(false) }

    Scaffold(scaffoldState = scaffoldState, content = {
        Box(modifier = Modifier.fillMaxSize()) {

        }
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
                        painterResource(id = R.drawable.back), White,
                        Black
                    ) {
                        navController.popBackStack()
                    }

                    MainTitle(
                        title = "Create your account!",
                        color = Black,
                        TextAlign.Center
                    )

                    SignUpAllTextLines(emailSignUp, usernameSignUp, passwordSignUp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.Center) {
                            Text(text = "I have read the", color = Gray)
                            Text(text = "Privacy Policy", color = Purple, modifier = Modifier
                                .padding(start = 4.dp)
                                .clickable {

                                })
                        }
                        Checkbox(
                            checked = checkBoxPolicy.value, onCheckedChange = {
                                checkBoxPolicy.value = it
                            }, colors = CheckboxDefaults.colors(
                                checkedColor = Purple
                            )
                        )
                    }

                    ButtonDesign(
                        text_color = White,
                        bg_color = Purple,
                        text_title = "GET STARTED"
                    ) {

                        if (!isValidEmail(emailSignUp.value)) {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(message = "Email is not valid!")
                            }
                        } else if (
                            usernameSignUp.value.trim() == ""
                        ) {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(message = "User name is not valid!")
                            }
                        } else if (
                            !isValidPassword(passwordSignUp.value)
                        ) {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(message = "Password is too weak!")
                            }
                        } else if (
                            !checkBoxPolicy.value
                        ) {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(message = "Checking Privacy policy is important!")
                            }
                        } else {
                            viewModel.signUp(
                                emailSignUp.value,
                                usernameSignUp.value,
                                passwordSignUp.value
                            )
                            buttonClicked.value = !buttonClicked.value
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "ALREADY HAVE AN ACCOUNT?", color = Gray)

                        Text(text = "SIGN IN", color = Purple, modifier = Modifier
                            .padding(start = 4.dp)
                            .clickable {
                                navController.navigate("SignIn")
                            })
                    }
                }
            }
        }
    })
    if (buttonClicked.value) {

        if (
            username.value.toString() !== "" &&
            emailResponse.value.toString() == emailSignUp.value &&
            tokenResponse.value.toString() !== ""
        ) {
//            viewModel.putLogin(true)
//            viewModel.putNickName(usernameSignUp.value)
            navController.navigate("WelcomeScreen/${usernameSignUp.value}")
            buttonClicked.value = !buttonClicked.value
        } else {
        }

    }
}


@Composable
fun SignUpAllTextLines(
    email: MutableState<String>,
    username: MutableState<String>,
    password: MutableState<String>

) {
    var showPassword = remember {
        mutableStateOf(false)
    }
    var icon = if (showPassword.value)
        painterResource(id = R.drawable.show_password_eye)
    else painterResource(id = R.drawable.show_password_eye)

    TextFieldDesign(
        username,
        "User name",
        painterResource(id = R.drawable.check),
        KeyboardType.Text,
        ImeAction.Next
    ) {
    }

    TextFieldDesign(
        email, "Email",
        painterResource(id = R.drawable.check),
        KeyboardType.Email,
        ImeAction.Next
    ) {
    }

    TextFieldPasswordDesign(
        value = password,
        hint = "Password",
        painterResource = icon,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done, showPassword
    )

}

fun isValidEmail(email: String): Boolean {
    val regexPattern = Regex("^([A-Za-z0-9_+\\-.])+@([A-Za-z0-9_+\\-.])+\\.([A-Za-z]{2,})$")
    return regexPattern.matches(email)
}

fun isValidPassword(password: String): Boolean {
    val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$&+.])(?=\\S+$).{8,}$")
    return passwordRegex.matches(password)
}


