package com.nistruct.meditation.spinnerTimePickerTools

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.nistruct.meditation.spinnerTimePickerTools.core.SnappedTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit


@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun DefaultWheelTimePicker(
    modifier: Modifier = Modifier,
    startTime: LocalTime = LocalTime.now(),
    minTime: LocalTime = LocalTime.MIN,
    maxTime: LocalTime = LocalTime.MAX,
    timeFormat: TimeFormat = TimeFormat.AM_PM,
    size: DpSize = DpSize(128.dp, 128.dp),
    rowCount: Int = 3,
    textColor: Color = LocalContentColor.current,
    selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
    onSnappedTime : (snappedTime: SnappedTime, timeFormat: TimeFormat) -> Int? = { _, _ -> null },
) {

    var snappedTime by remember { mutableStateOf(startTime.truncatedTo(ChronoUnit.MINUTES)) }

    val hours = (0..23).map {
        Hour(
            text = it.toString().padStart(2, '0'),
            value = it,
            index = it
        )
    }
    val amPmHours = (1..12).map {
        AmPmHour(
            text = it.toString(),
            value = it,
            index = it - 1
        )
    }

    val minutes = (0..59).map {
        Minute(
            text = it.toString().padStart(2, '0'),
            value = it,
            index = it
        )
    }

    val amPms = listOf(
        AmPm(
            text = "AM",
            value = AmPmValue.AM,
            index = 0
        ),
        AmPm(
            text = "PM",
            value = AmPmValue.PM,
            index = 1
        )
    )

    var snappedAmPm by remember {
        mutableStateOf(
            amPms.find { it.value == amPmValueFromTime(startTime) } ?: amPms[0]
        )
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center){
        if(selectorProperties.enabled().value){
            Surface(
                modifier = Modifier
                    .fillMaxWidth().height(size.height / rowCount),
                shape = RoundedCornerShape(5.dp)
            ) {}
        }
        Row {
            //Hour
            WheelTextPicker(
                size = DpSize(
                    width = size.width / if(timeFormat == TimeFormat.AM_PM) 2 else 3,
                    height = size.height
                ),
                texts = if(timeFormat == TimeFormat.AM_PM) hours.map { it.text } else amPmHours.map { it.text },
                rowCount = rowCount,
                color = textColor,
                startIndex =  if(timeFormat == TimeFormat.AM_PM) {
                    hours.find { it.value == startTime.hour }?.index ?: 0
                } else amPmHours.find { it.value ==  localTimeToAmPmHour(startTime) }?.index ?: 0,
                selectorProperties = WheelPickerDefaults.selectorProperties(
                    enabled = false
                ),
                onScrollFinished = { snappedIndex ->

                    val newHour = if(timeFormat == TimeFormat.AM_PM) {
                        hours.find { it.index == snappedIndex }?.value
                    } else {
                        amPmHourToHour24(
                            amPmHours.find { it.index == snappedIndex }?.value ?: 0,
                            snappedTime.minute,
                            snappedAmPm.value
                        )
                    }

                    newHour?.let {

                        val newTime = snappedTime.withHour(newHour)

                        if(!newTime.isBefore(minTime) && !newTime.isAfter(maxTime)) {
                            snappedTime = newTime
                        }

                        val newIndex = if(timeFormat == TimeFormat.AM_PM) {
                            hours.find { it.value == snappedTime.hour }?.index
                        } else {
                            amPmHours.find { it.value ==  localTimeToAmPmHour(snappedTime)}?.index
                        }

                        newIndex?.let {
                            onSnappedTime(
                                SnappedTime.Hour(
                                    localTime = snappedTime,
                                    index = newIndex
                                ),
                                timeFormat
                            )?.let { return@WheelTextPicker it }
                        }
                    }

                    return@WheelTextPicker if(timeFormat == TimeFormat.AM_PM) {
                        hours.find { it.value == snappedTime.hour }?.index
                    } else {
                        amPmHours.find { it.value ==  localTimeToAmPmHour(snappedTime)}?.index
                    }
                }
            )
            //Minute
            WheelTextPicker(
                size = DpSize(
                    width = size.width / if(timeFormat == TimeFormat.AM_PM) 2 else 3,
                    height = size.height
                ),
                texts = minutes.map { it.text },
                rowCount = rowCount,
                color = textColor,
                startIndex = minutes.find { it.value == startTime.minute }?.index ?: 0,
                selectorProperties = WheelPickerDefaults.selectorProperties(
                    enabled = false
                ),
                onScrollFinished = { snappedIndex ->

                    val newMinute = minutes.find { it.index == snappedIndex }?.value

                    val newHour = if(timeFormat == TimeFormat.AM_PM) {
                        hours.find { it.value == snappedTime.hour }?.value
                    } else {
                        amPmHourToHour24(
                            amPmHours.find { it.value == localTimeToAmPmHour(snappedTime) }?.value ?: 0,
                            snappedTime.minute,
                            snappedAmPm.value
                        )
                    }

                    newMinute?.let {
                        newHour?.let {
                            val newTime = snappedTime.withMinute(newMinute).withHour(newHour)

                            if(!newTime.isBefore(minTime) && !newTime.isAfter(maxTime)) {
                                snappedTime = newTime
                            }

                            val newIndex = minutes.find { it.value == snappedTime.minute }?.index

                            newIndex?.let {
                                onSnappedTime(
                                    SnappedTime.Minute(
                                        localTime = snappedTime,
                                        index = newIndex
                                    ),
                                    timeFormat
                                )?.let { return@WheelTextPicker it }
                            }
                        }
                    }

                    return@WheelTextPicker minutes.find { it.value == snappedTime.minute }?.index
                }
            )
            //AM_PM
            if(timeFormat == TimeFormat.AM_PM) {
                WheelTextPicker(
                    size = DpSize(
                        width = size.width / 3,
                        height = size.height
                    ),
                    texts = amPms.map { it.text },
                    rowCount = rowCount,

                    color = textColor,
                    startIndex = amPms.find { it.value == amPmValueFromTime(startTime) }?.index ?:0,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = false
                    ),
                    onScrollFinished = { snappedIndex ->

                        val newAmPm =  amPms.find {
                            if(snappedIndex == 2) {
                                it.index == 1
                            } else {
                                it.index == snappedIndex
                            }
                        }

                        newAmPm?.let {
                            snappedAmPm = newAmPm
                        }

                        val newMinute = minutes.find { it.value == snappedTime.minute }?.value

                        val newHour = amPmHourToHour24(
                            amPmHours.find { it.value == localTimeToAmPmHour(snappedTime) }?.value ?: 0,
                            snappedTime.minute,
                            snappedAmPm.value
                        )

                        newMinute?.let {
                            val newTime = snappedTime.withMinute(newMinute).withHour(newHour)

                            if(!newTime.isBefore(minTime) && !newTime.isAfter(maxTime)) {
                                snappedTime = newTime
                            }

                            val newIndex = minutes.find { it.value == snappedTime.hour }?.index

                            newIndex?.let {
                                onSnappedTime(
                                    SnappedTime.Hour(
                                        localTime = snappedTime,
                                        index = newIndex
                                    ),
                                    timeFormat
                                )
                            }
                        }

                        return@WheelTextPicker snappedIndex
                    }
                )
            }
        }

    }
}

enum class TimeFormat {
     AM_PM
}

private data class Hour(
    val text: String,
    val value: Int,
    val index: Int
)
private data class AmPmHour(
    val text: String,
    val value: Int,
    val index: Int
)

@RequiresApi(Build.VERSION_CODES.O)
internal fun localTimeToAmPmHour(localTime: LocalTime): Int {

    if(
        isBetween(
            localTime,
            LocalTime.of(0,0),
            LocalTime.of(0,59)
        )
    ) {
        return localTime.hour + 12
    }

    if(
        isBetween(
            localTime,
            LocalTime.of(1,0),
            LocalTime.of(11,59)
        )
    ) {
        return localTime.hour
    }

    if(
        isBetween(
            localTime,
            LocalTime.of(12,0),
            LocalTime.of(12,59)
        )
    ) {
        return localTime.hour
    }

    if(
        isBetween(
            localTime,
            LocalTime.of(13,0),
            LocalTime.of(23,59)
        )
    ) {
        return localTime.hour - 12
    }

    return localTime.hour
}

@RequiresApi(Build.VERSION_CODES.O)
private fun isBetween(localTime: LocalTime, startTime: LocalTime, endTime: LocalTime): Boolean {
    return localTime in startTime..endTime
}

private fun amPmHourToHour24(amPmHour: Int, amPmMinute: Int, amPmValue: AmPmValue): Int {

    return when(amPmValue) {
        AmPmValue.AM -> {
            if(amPmHour == 12 && amPmMinute <= 59) {
                0
            } else {
                amPmHour
            }
        }
        AmPmValue.PM -> {
            if(amPmHour == 12 && amPmMinute <= 59) {
                amPmHour
            } else {
                amPmHour + 12
            }
        }
    }
}

private data class Minute(
    val text: String,
    val value: Int,
    val index: Int
)

private data class AmPm(
    val text: String,
    val value: AmPmValue,
    val index: Int?
)

internal enum class AmPmValue {
    AM, PM
}

@RequiresApi(Build.VERSION_CODES.O)
private fun amPmValueFromTime(time: LocalTime): AmPmValue {
    return if(time.hour > 11) AmPmValue.PM else AmPmValue.AM
}


