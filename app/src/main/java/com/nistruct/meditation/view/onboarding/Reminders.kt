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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nistruct.meditation.DesignContent.ButtonDesign
import com.nistruct.meditation.spinnerTimePickerTools.WheelTimePicker
import com.nistruct.meditation.ui.theme.*
import java.time.LocalTime


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

    val wheelPickerValue = remember { mutableStateOf(LocalTime.now()) }

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
                //    style = MaterialTheme.typography.h6,
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
                // style = MaterialTheme.typography.h6,
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
                Card(
                    modifier = Modifier
                        .size(55.dp)
                        .padding(all = 10.dp),
                    shape = CircleShape,
                    elevation = 7.dp,
                    border = BorderStroke(1.dp, Gray_level1),

                    ) {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {

                                if (sundayClicked.value) {
                                    sundayText.value = Black
                                    sundayClicked.value = false
                                } else {
                                    sundayText.value = White
                                    sundayClicked.value = true
                                }

                            }
                            .background(
                                if (sundayClicked.value) Black
                                else White
                            )
                    ) {
                        Text(text = "SU", textAlign = TextAlign.Center, color = sundayText.value)
                    }

                }

                Card(
                    modifier = Modifier
                        .size(55.dp)
                        .padding(all = 10.dp),
                    shape = CircleShape,
                    elevation = 7.dp,
                    border = BorderStroke(1.dp, Gray_level1),

                    ) {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {

                                if (mondayClicked.value) {
                                    mondayText.value = Black
                                    mondayClicked.value = false
                                } else {
                                    mondayText.value = White
                                    mondayClicked.value = true
                                }

                            }
                            .background(
                                if (mondayClicked.value) Black
                                else White
                            )
                    ) {
                        Text(text = "M", textAlign = TextAlign.Center, color = mondayText.value)
                    }

                }

                Card(
                    modifier = Modifier
                        .size(55.dp)
                        .padding(all = 10.dp),
                    shape = CircleShape,
                    elevation = 7.dp,
                    border = BorderStroke(1.dp, Gray_level1),

                    ) {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {

                                if (tuesdayClicked.value) {
                                    tuesdayText.value = Black
                                    tuesdayClicked.value = false
                                } else {
                                    tuesdayText.value = White
                                    tuesdayClicked.value = true
                                }

                            }
                            .background(
                                if (tuesdayClicked.value) Black
                                else White
                            )
                    ) {
                        Text(text = "T", textAlign = TextAlign.Center, color = tuesdayText.value)
                    }

                }


                Card(
                    modifier = Modifier
                        .size(55.dp)
                        .padding(all = 10.dp),
                    shape = CircleShape,
                    elevation = 7.dp,
                    border = BorderStroke(1.dp, Gray_level1),

                    ) {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {

                                if (wednesdayClicked.value) {
                                    wednesdayText.value = Black
                                    wednesdayClicked.value = false
                                } else {
                                    wednesdayText.value = White
                                    wednesdayClicked.value = true
                                }

                            }
                            .background(
                                if (wednesdayClicked.value) Black
                                else White
                            )
                    ) {
                        Text(text = "W", textAlign = TextAlign.Center, color = wednesdayText.value)
                    }

                }

                Card(
                    modifier = Modifier
                        .size(55.dp)
                        .padding(all = 10.dp),
                    shape = CircleShape,
                    elevation = 7.dp,
                    border = BorderStroke(1.dp, Gray_level1),

                    ) {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {

                                if (thursdayClicked.value) {
                                    thursdayText.value = Black
                                    thursdayClicked.value = false
                                } else {
                                    thursdayText.value = White
                                    thursdayClicked.value = true
                                }
                            }
                            .background(
                                if (thursdayClicked.value) Black
                                else White
                            )
                    ) {
                        Text(text = "TH", textAlign = TextAlign.Center, color = thursdayText.value)
                    }
                }

                Card(
                    modifier = Modifier
                        .size(55.dp)
                        .padding(all = 10.dp),
                    shape = CircleShape,
                    elevation = 7.dp,
                    border = BorderStroke(1.dp, Gray_level1),

                    ) {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {

                                if (fridayClicked.value) {
                                    fridayText.value = Black
                                    fridayClicked.value = false
                                } else {
                                    fridayText.value = White
                                    fridayClicked.value = true
                                }

                            }
                            .background(
                                if (fridayClicked.value) Black
                                else White
                            )
                    ) {
                        Text(text = "F", textAlign = TextAlign.Center, color = fridayText.value)
                    }

                }
                Card(
                    modifier = Modifier
                        .size(55.dp)
                        .padding(all = 10.dp),
                    shape = CircleShape,
                    elevation = 7.dp,
                    border = BorderStroke(1.dp, Gray_level1),

                    ) {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {

                                if (saturdayClicked.value) {
                                    saturdayText.value = Black
                                    saturdayClicked.value = false
                                } else {
                                    saturdayText.value = White
                                    saturdayClicked.value = true
                                }

                            }
                            .background(
                                if (saturdayClicked.value) Black
                                else White
                            )
                    ) {
                        Text(text = "S", textAlign = TextAlign.Center, color = saturdayText.value)
                    }

                }

            }


            ButtonDesign(text_color = White, bg_color = Purple, text_title = "SAVE") {
                
                navController.navigate("Meditate")
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

}








