package jp.ac.it_college.std.s23024.countdowntimer.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class AppUiState(
    var time: MutableState<Long> = mutableStateOf(3 * 60 * 1000),
    var timeLeft: MutableState<Long> = mutableStateOf(time.value),
    var isRunning: MutableState<Boolean> = mutableStateOf(false)
)

