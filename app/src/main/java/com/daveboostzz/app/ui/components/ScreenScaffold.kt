package com.daveboostzz.app.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daveboostzz.app.ui.theme.*

@Composable
fun ScreenScaffold(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 18.dp, vertical = 16.dp),
        content = {
            Text(title, style = MaterialTheme.typography.headlineSmall, color = TextWhite)
            if (!subtitle.isNullOrBlank()) {
                Spacer(Modifier.height(4.dp))
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MutedGrey)
            }
            Spacer(Modifier.height(18.dp))
            content()
        }
    )
}
