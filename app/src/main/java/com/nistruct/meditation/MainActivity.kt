package com.nistruct.meditation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nistruct.meditation.ui.theme.MeditationAppTheme
import com.nistruct.meditation.view.*
import com.nistruct.meditation.view.laterstage.HomePage
import com.nistruct.meditation.view.laterstage.Music
import com.nistruct.meditation.view.laterstage.Sleep
import com.nistruct.meditation.view.laterstage.WelcomeSleep
import com.nistruct.meditation.view.meditations.CourseDetails
import com.nistruct.meditation.view.meditations.MeditateV2
import com.nistruct.meditation.view.meditations.MusicV2
import com.nistruct.meditation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(//all the screen size
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    PageTransmitions()

                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PageTransmitions(
) {
    var viewModel: UserViewModel = hiltViewModel()

    var startDestination = "LaunchScreen"

//    if (viewModel.getLogin()) startDestination = "ChooseTopic"

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable("LaunchScreen") {
            SignUpAndSignIn(navController)
        }
        composable("signUp") {
            SignUp(navController)
        }
        composable("SignIn") {
            SignIn(navController)
        }
        composable(
            "WelcomeScreen/{getNickname}",
            arguments = listOf(navArgument("getNickname") { type = NavType.StringType })
        ) {
            val getNickName = it.arguments?.getString("getNickname")!!
            WelcomeScreen(navController, getNickName)
        }

        composable("ChooseTopic") {
            ChooseTopic(navController)
        }
        composable(
            "Reminders/{chosenTopic}",
            arguments = listOf(navArgument("chosenTopic") { type = NavType.StringType })
        ) {
            val chosenTopic = it.arguments?.getString("chosenTopic")!!
            Reminders(navController = navController, chosenTopic)
        }
        composable("MeditateV2") {
            MeditateV2(navController = navController)
        }
        composable("CourseDetails") {
            CourseDetails(navController = navController)
        }

        composable(
            "MusicV2/{musicName}",
            arguments = listOf(navArgument("musicName") { type = NavType.StringType })
        ) {
            val musicName = it.arguments?.getString("musicName")!!
            MusicV2(navController, musicName)
        }

        composable("HomePage") {
            HomePage(navController = navController)
        }
        composable("MainViewLaterStage") {
            MainViewLaterStage(navController = navController)
        }
        composable("WelcomeSleep") {
            WelcomeSleep(navController = navController)
        }
        composable("Sleep") {
            Sleep(navController = navController)
        }
        composable("Music") {
            Music(navController = navController)
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeditationAppTheme() {
    }
}