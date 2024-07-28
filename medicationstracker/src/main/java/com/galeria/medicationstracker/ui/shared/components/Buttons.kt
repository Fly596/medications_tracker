package com.galeria.medicationstracker.ui.shared.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.R


@Composable
fun HIGButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    style: HIGButtonStyle = HIGButtonStyle.Primary
) {
    val buttonColors = when (style) {
        HIGButtonStyle.Primary -> ButtonDefaults.buttonColors(
            containerColor = Color(0xFF007AFF),
            contentColor = Color.White
        )

        HIGButtonStyle.Secondary -> ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color(0xFF007AFF),
            disabledContainerColor = Color.LightGray
        )
    }

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        border = BorderStroke(1.dp, Color.Transparent), // Remove default border
        shape = RoundedCornerShape(8.dp),
        colors = buttonColors
    ) {
        Text(text = text)
    }
}

enum class HIGButtonStyle {
    Primary,
    Secondary
}

@Preview(name = "Buttons")
@Composable
private fun PreviewButtons() {
    HIGButton(stringResource(R.string.button_default_text), {}, style = HIGButtonStyle.Primary)
}