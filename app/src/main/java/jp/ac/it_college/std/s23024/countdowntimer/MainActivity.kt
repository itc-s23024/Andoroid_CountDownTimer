package jp.ac.it_college.std.s23024.countdowntimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.ac.it_college.std.s23024.countdowntimer.ui.AppViewModel
import jp.ac.it_college.std.s23024.countdowntimer.ui.Arc
import jp.ac.it_college.std.s23024.countdowntimer.ui.BottomBar
import jp.ac.it_college.std.s23024.countdowntimer.ui.TopBar
import jp.ac.it_college.std.s23024.countdowntimer.ui.theme.CountDownTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountDownTimerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScaffold()
                }
            }
        }
    }
}

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel()
    ) {
    val uiState = viewModel.uiState
    val iconOnClick: (Int) -> Unit = { time ->
        viewModel.addTime(time)
    }
    val toggleTimer = {
        if (uiState.isRunning.value) {
            viewModel.stopTimer()
        } else {
            viewModel.startTimer(viewModel.uiState.time.value)
        }
    }
    Scaffold(
        topBar = {
            TopBar(iconOnClick = iconOnClick)
                 },
        bottomBar = {
            BottomBar(iconOnClick = iconOnClick)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = toggleTimer
            ) {
                Icon(
                    imageVector = Icons.Filled.Timer,
                    contentDescription = stringResource(id = R.string.fav_description)
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            val remain = uiState.timeLeft.value.toFloat()
            val total = uiState.time.value.toFloat()
            Arc(
                color = MaterialTheme.colorScheme.primary,
                timeLeft = remain / total
            )
            val minute = uiState.timeLeft.value / 1000L / 60L
            val second = uiState.timeLeft.value / 1000L % 60L
            Text(
                text = stringResource(R.string.time_format, minute, second),
                fontSize = 100.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(device = "spec:width=411dp,height=891dp")
@Composable
private fun AppScaffoldPreview() {
    CountDownTimerTheme {
        AppScaffold()
    }
}