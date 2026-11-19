package com.daveboostzz.app.ui.components
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import com.daveboostzz.app.ui.theme.*

@Composable
fun NeonGauge(value: Float, modifier: Modifier = Modifier) {
    val safeValue = if (value.isFinite()) value.coerceIn(0f, 1f) else 0f
    val animated by animateFloatAsState(safeValue, label = "gauge")
    Canvas(modifier.size(116.dp)) {
        val stroke = 10.dp.toPx()
        drawArc(
            color = CharcoalElevated,
            startAngle = 135f,
            sweepAngle = 270f,
            useCenter = false,
            style = Stroke(stroke, cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
        drawArc(
            color = NeonRed,
            startAngle = 135f,
            sweepAngle = 270f * animated,
            useCenter = false,
            style = Stroke(stroke, cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
    }
}
