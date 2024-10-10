package jp.ac.it_college.std.s23024.countdowntimer.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.max
import kotlin.math.min

@Composable
fun Arc(
    modifier: Modifier = Modifier,
    color: Color,
    timeLeft: Float
) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val strokeWidth = min(size.width, size.height) / 20.0f
        val minSize = min(size.width, size.height) - strokeWidth
        val maxSize = max(size.width, size.height)
        val offset: Offset = if (size.width < size.height) {
            Offset(strokeWidth / 2.0f, (maxSize - minSize) / 2.0f)
        } else {
            Offset((maxSize - minSize) / 2.0f, strokeWidth / 2.0f)
        }
        drawArc(
            color = color,
            startAngle = -90.0f,
            sweepAngle = timeLeft * 360.0f,
            useCenter = false,
            topLeft = offset,
            size = Size(minSize,minSize),
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            )
        )
        
    }
}

@Preview(device = "spec:width=150dp,height=100dp,dpi=440")
@Composable
private fun ArcPreview1() {
    Arc(color = Color.Green, timeLeft = 1.0f)
}

@Preview(device = "spec:width=200dp,height=300dp,dpi=440")
@Composable
private fun ArcPreview2() {
    Arc(color = Color.Red, timeLeft = .75f)
}

@Preview(device = "spec:width=300dp,height=200dp,dpi=440")
@Composable
private fun ArcPreview3() {
    Arc(color = Color.Blue, timeLeft = .5f)
}