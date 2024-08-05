package com.galeria.medicationstracker.ui.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme

enum class HIGButtonStyle {
    Filled,
    Bezeled,
    Borderless,
}

// TODO: вынести цвета в тему.
@Composable
fun HIGButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    style: HIGButtonStyle = HIGButtonStyle.Filled
) {
    val buttonColors =
        when (style) {
            HIGButtonStyle.Filled ->
                ButtonDefaults.buttonColors(
                    containerColor = MedicationsTrackerAppTheme.extendedColorsLight.extBlue.defaultLight,
                    contentColor = Color.White,
                    disabledContainerColor = Color(0x1f787880),
                    disabledContentColor = Color(0x4d3c3c43))

            HIGButtonStyle.Bezeled ->
                ButtonDefaults.buttonColors(
                    containerColor = Color(0x26007aff), /*Fills, tertiary*/
                    contentColor =
                        MedicationsTrackerAppTheme.extendedColorsLight.extBlue
                            .defaultLight, /*Labels, tertiary*/
                    disabledContainerColor = Color(0x1f787880),
                    disabledContentColor = Color(0x4d3c3c43))

            HIGButtonStyle.Borderless ->
                ButtonDefaults.textButtonColors(
                    contentColor = MedicationsTrackerAppTheme.extendedColorsLight.extBlue.defaultLight,
                    disabledContentColor = Color(0x4d3c3c43))
        }

    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius_large)),
        colors = buttonColors) {
            Text(
                text = text,
                style = MedicationsTrackerAppTheme.extendedTypography.body,
            )
        }
}

@Composable
fun HIGListButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    style: HIGButtonStyle = HIGButtonStyle.Bezeled,
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowForwardIos
) {
    val buttonColors =
        when (style) {
            HIGButtonStyle.Filled ->
                ButtonDefaults.elevatedButtonColors(
                    containerColor =
                        MedicationsTrackerAppTheme.systemColorsLight
                            .backgroundLightPrimaryGrouped, /*Fills, tertiary*/
                    contentColor = Color.White,
                    disabledContainerColor = Color(0x1f787880),
                    disabledContentColor = Color(0x4d3c3c43))

            HIGButtonStyle.Bezeled ->
                ButtonDefaults.elevatedButtonColors(
                    containerColor =
                        MedicationsTrackerAppTheme.systemColorsLight
                            .backgroundLightPrimaryGrouped, /*Fills, tertiary*/
                    contentColor = MedicationsTrackerAppTheme.extendedColorsLight.extBlack.defaultLight,
                    disabledContainerColor = Color(0x1f787880),
                    disabledContentColor = Color(0x4d3c3c43))

            HIGButtonStyle.Borderless ->
                ButtonDefaults.elevatedButtonColors(
                    contentColor = MedicationsTrackerAppTheme.extendedColorsLight.extBlue.defaultLight,
                    disabledContentColor = Color(0x4d3c3c43))
        }

    ElevatedButton(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(dimensionResource(R.dimen.list_corner_radius_default)),
        colors = buttonColors,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Text(
                text = text,
                style = MedicationsTrackerAppTheme.extendedTypography.body,
                modifier = Modifier.padding(vertical = 8.dp))

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = icon,
                contentDescription = "Show options",
                tint = MedicationsTrackerAppTheme.extendedColorsLight.extGray.defaultLight,
                modifier = Modifier.size(16.dp))
        }
}

@Preview(
    name = "Buttons",
    showBackground = false,
    showSystemUi = true,
    device = "spec:parent=pixel_8,navigation=buttons")
@Composable
private fun PreviewButtons() {
    MedicationsTrackerAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
                Row {
                    HIGListButton(
                        stringResource(R.string.button_default_text),
                        {},
                        enabled = true,
                        // style = HIGButtonStyle.Filled
                    )
                }

                Row {
                    HIGButton(
                        stringResource(R.string.button_default_text),
                        {},
                        enabled = true,
                        style = HIGButtonStyle.Filled)
                    HIGButton(
                        stringResource(R.string.button_default_text),
                        {},
                        enabled = false,
                        style = HIGButtonStyle.Filled)
                }

                Row {
                    HIGButton(
                        stringResource(R.string.button_default_text),
                        {},
                        enabled = true,
                        style = HIGButtonStyle.Bezeled)

                    HIGButton(
                        stringResource(R.string.button_default_text),
                        {},
                        enabled = false,
                        style = HIGButtonStyle.Bezeled)
                }
                Row {
                    HIGButton(
                        stringResource(R.string.button_default_text),
                        {},
                        enabled = true,
                        style = HIGButtonStyle.Borderless)

                    HIGButton(
                        stringResource(R.string.button_default_text),
                        {},
                        enabled = false,
                        style = HIGButtonStyle.Borderless)
                }
            }
    }
}
