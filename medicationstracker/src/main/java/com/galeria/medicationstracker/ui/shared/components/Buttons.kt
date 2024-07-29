package com.galeria.medicationstracker.ui.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.theme.SpeechRecognitionAppTheme

// TODO: вынести цвета в тему.
@Composable
fun HIGButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    style: HIGButtonStyle = HIGButtonStyle.Filled
) {
    val buttonColors = when (style) {
        HIGButtonStyle.Filled -> ButtonDefaults.buttonColors(
            containerColor = Color(0xFF007AFF),
            contentColor = Color.White,
            disabledContainerColor = Color(0x1f787880),
            disabledContentColor = Color(0x4d3c3c43)
        )

        HIGButtonStyle.Bezeled -> ButtonDefaults.buttonColors(
            containerColor = Color(0x26007aff),  /*Fills, tertiary*/
            contentColor = Color(0xFF007AFF), /*Labels, tertiary*/
            disabledContainerColor = Color(0x1f787880),
            disabledContentColor = Color(0x4d3c3c43)
        )

        HIGButtonStyle.Borderless -> ButtonDefaults.textButtonColors(
            contentColor = Color(0xFF007AFF),
            disabledContentColor = Color(0x4d3c3c43)
        )
    }

    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius_small)),
        colors = buttonColors
    ) {
        Text(text = text)
    }
}

enum class HIGButtonStyle {
    Filled,
    Bezeled,
    Borderless,
}

@Preview(
    name = "Buttons", showBackground = false, showSystemUi = true,
    device = "spec:parent=pixel_8,navigation=buttons"
)
@Composable
private fun PreviewButtons() {
    SpeechRecognitionAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Row {
                HIGButton(
                    stringResource(R.string.button_default_text),
                    {},
                    enabled = true,
                    style = HIGButtonStyle.Filled
                )
                HIGButton(
                    stringResource(R.string.button_default_text),
                    {},
                    enabled = false,
                    style = HIGButtonStyle.Filled
                )
            }

            Row {

                HIGButton(
                    stringResource(R.string.button_default_text),
                    {},
                    enabled = true,
                    style = HIGButtonStyle.Bezeled
                )

                HIGButton(
                    stringResource(R.string.button_default_text),
                    {},
                    enabled = false,
                    style = HIGButtonStyle.Bezeled
                )
            }
            Row {

                HIGButton(
                    stringResource(R.string.button_default_text),
                    {},
                    enabled = true,
                    style = HIGButtonStyle.Borderless
                )

                HIGButton(
                    stringResource(R.string.button_default_text),
                    {},
                    enabled = false,
                    style = HIGButtonStyle.Borderless
                )
            }

        }
    }
}