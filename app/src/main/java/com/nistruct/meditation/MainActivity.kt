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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nistruct.meditation.ui.theme.MeditationAppTheme
import com.nistruct.meditation.view.*
import com.nistruct.meditation.view.meditations.CourseDetails
import com.nistruct.meditation.view.meditations.Meditate
import com.nistruct.meditation.view.meditations.Music
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder


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
                    PageTransitions()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PageTransitions(
) {
    var startDestination = "LaunchScreen"
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("LaunchScreen") {
            LaunchScreen(navController)
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
        composable("Meditate") {
            Meditate(navController = navController)
        }
        composable(
            "CourseDetails/{meditationId}",
            arguments = listOf(navArgument("meditationId") { type = NavType.StringType })
        ) {
            val meditationId = it.arguments?.getString("meditationId")!!

            CourseDetails(navController = navController, meditationId)
        }
        composable(
            "Music/{songName}/{songUrl}",
            arguments = listOf(
                navArgument("songName") { type = NavType.StringType },
                navArgument("songUrl") { type = NavType.StringType })
        ) {
            val songName = it.arguments?.getString("songName")!!
            val encodedSongUrl = it.arguments?.getString("songUrl")!!
            val songUrl = URLDecoder.decode(encodedSongUrl, "UTF-8")
            Music(navController, songName, songUrl)
        }
        composable("Account") {
            Logout(navController)
        }
    }
}