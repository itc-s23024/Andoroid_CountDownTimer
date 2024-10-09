package jp.ac.it_college.std.s23024.countdowntimer.logic

import android.content.IntentSender.OnFinished
import android.os.CountDownTimer
import androidx.compose.ui.graphics.Interval
import java.util.concurrent.Future

class MyCountDownTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    val changed: (Long) -> Unit,
    val finished: () -> Unit,
) : CountDownTimer(millisInFuture, countDownInterval) {

    override fun onTick(millisUntilFinished: Long) {
        changed(millisUntilFinished)
    }

    override fun onFinish() {
        finished()
    }

}