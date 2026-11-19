package com.daveboostzz.app.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DaveColors = darkColorScheme(
    primary = NeonRed,
    onPrimary = TextWhite,
    secondary = BrightRed,
    background = PureBlack,
    onBackground = TextWhite,
    surface = Charcoal,
    onSurface = TextWhite,
    error = BrightRed,
    onError = TextWhite
)

@Composable
fun DaveBoostTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DaveColors, content = content)
}
