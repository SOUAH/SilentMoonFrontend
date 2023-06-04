package com.nistruct.meditation.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nistruct.meditation.DesignContent.ButtonDesign
import com.nistruct.meditation.spinnerTimePickerTools.WheelTimePicker
import com.nistruct.meditation.ui.theme.*
import com.nistruct.meditation.viewmodel.UserViewModel
import java.time.LocalTime

data class Day(
    val name: String,
    val shortName: String,
    val clicked: MutableState<Boolean>,
    val color: MutableState<Color>
)


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Reminders(navController: NavHostController, chosenTopic: String) {

    val mondayClicked = remember { mutableStateOf(false) }
    val tuesdayClicked = remember { mutableStateOf(false) }
    val wednesdayClicked = remember { mutableStateOf(false) }
    val thursdayClicked = remember { mutableStateOf(false) }
    val fridayClicked = remember { mutableStateOf(false) }
    val saturdayClicked = remember { mutableStateOf(false) }
    val sundayClicked = remember { mutableStateOf(false) }

    val mondayText = remember { mutableStateOf(Black) }
    val tuesdayText = remember { mutableStateOf(Black) }
    val wednesdayText = remember { mutableStateOf(Black) }
    val thursdayText = remember { mutableStateOf(Black) }
    val fridayText = remember { mutableStateOf(Black) }
    val saturdayText = remember { mutableStateOf(Black) }
    val sundayText = remember { mutableStateOf(Black) }

    val daysOfWeek = remember {
        listOf(
            Day("Sunday", "SU", mondayClicked, mondayText),
            Day("Monday", "M", tuesdayClicked, tuesdayText),
            Day("Tuesday", "T", wednesdayClicked, wednesdayText),
            Day("Wednesday", "W", thursdayClicked, thursdayText),
            Day("Thursday", "TH", fridayClicked, fridayText),
            Day("Friday", "F", saturdayClicked, saturdayText),
            Day("Saturday", "S", sundayClicked, sundayText)
        )
    }

    val wheelPickerValue = remember { mutableStateOf(LocalTime.now()) }

    var buttonClicked = remember { mutableStateOf(false) }

    var viewModel: UserViewModel = hiltViewModel()

    var favTopic = viewModel.favTopic.observeAsState()
    var notificationDays = viewModel.notificationDays.observeAsState()

    val showDialog = remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "What time would you like to meditate?",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Start,
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 40.dp)
            )
            Text(
                text = "Any time you can choose but We recommend first thing in the morning.",
                textAlign = TextAlign.Start,
                color = Gray,
                modifier = Modifier.padding(start = 20.dp, end = 40.dp)
            )
            Card(
                modifier = Modifier
                    .padding(all = 20.dp)
                    .fillMaxWidth(),
                elevation = 5.dp,
                backgroundColor = Gray_level1,
                shape = RoundedCornerShape(20)
            ) {
                Row(horizontalArrangement = Arrangement.Center)
                {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        WheelTimePicker { snappedTime ->
                            wheelPickerValue.value = snappedTime
                        }
                    }
                }
            }
            Text(
                text = "Which day would you like to meditate?",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Start,
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 40.dp)
            )
            Text(
                text = "Every day is best, but we recommend picking at least five.",
                textAlign = TextAlign.Start,
                color = Gray,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 40.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                daysOfWeek.forEach { day ->
                    DayCard(day)
                }
            }
            ButtonDesign(text_color = White, bg_color = Purple, text_title = "SAVE") {
                if (daysOfWeek.any { it.clicked.value }) {
                    viewModel.updateUser(
                        chosenTopic,
                        daysOfWeek.filter { it.clicked.value }.map { it.name }.toTypedArray(),
                        wheelPickerValue.value
                    )
                    buttonClicked.value = !buttonClicked.value
                } else {
                    showDialog.value = true
                }
            }

            Text(
                text = "NO THANKS",
                color = Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.navigate("LaunchScreen")
                    }
            )
        }
    }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Error") },
            text = { Text(text = "Please select both the time and at least one day.") },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("OK")
                }
            }
        )
    }
    if (buttonClicked.value) {
        if (favTopic.value.toString() == chosenTopic
            && notificationDays.value?.size!! > 0
        ) {
            navController.navigate("Meditate")
            buttonClicked.value = !buttonClicked.value
        } else {
        }
    }
}

@Composable
fun DayCard(day: Day) {
    Card(
        modifier = Modifier
            .size(55.dp)
            .padding(all = 10.dp),
        shape = CircleShape,
        elevation = 7.dp,
        border = BorderStroke(1.dp, Gray_level1)
    ) {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    if (day.clicked.value) {
                        day.color.value = Black
                        day.clicked.value = false
                    } else {
                        day.color.value = White
                        day.clicked.value = true
                    }
                }
                .background(
                    if (day.clicked.value) Black
                    else White
                )
        ) {
            Text(text = day.shortName, textAlign = TextAlign.Center, color = day.color.value)
        }
    }
}










