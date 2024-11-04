package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.theme.ButtonShape
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme.colors

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
  style: HIGButtonStyle = HIGButtonStyle.Filled,
  modifier: Modifier = Modifier,
) {
  val buttonColors =
    when (style) {
      HIGButtonStyle.Filled ->
        ButtonDefaults.buttonColors(
          containerColor = colors.primary400,
          contentColor = colors.sysWhite,
          disabledContainerColor = colors.tertiaryFill,
          disabledContentColor = colors.tertiaryLabel,
        )

      HIGButtonStyle.Bezeled ->
        ButtonDefaults.buttonColors(
          containerColor = colors.primary400, /*Fills, tertiary*/
          contentColor = colors.primaryLabelDark,
          /*.defaultLight*/
          /*Labels, tertiary*/
          disabledContainerColor = Color(0x1f787880),
          disabledContentColor = Color(0x4d3c3c43),
        )

      HIGButtonStyle.Borderless ->
        ButtonDefaults.outlinedButtonColors(
          contentColor = MedTrackerTheme.colors.primary400,
          disabledContentColor = Color(0x4d3c3c43),
        )
    }

  Button(
    onClick = onClick,
    enabled = enabled,
    shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius_large)),
    modifier = modifier,
    colors = buttonColors,
  ) {
    Text(text = text, style = MedTrackerTheme.typography.body)
  }
}

@Composable
fun HIGListButton(
  text: String,
  onClick: () -> Unit,
  enabled: Boolean = true,
  style: HIGButtonStyle = HIGButtonStyle.Bezeled,
  icon: ImageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
) {
  val buttonColors =
    when (style) {
      HIGButtonStyle.Filled ->
        ButtonDefaults.elevatedButtonColors(
          containerColor = MedTrackerTheme.colors.primaryBackgroundGrouped,
          /*Fills, tertiary*/
          contentColor = Color.White,
          disabledContainerColor = Color(0x1f787880),
          disabledContentColor = Color(0x4d3c3c43),
        )

      HIGButtonStyle.Bezeled ->
        ButtonDefaults.elevatedButtonColors(
          containerColor = MedTrackerTheme.colors.primaryBackgroundGrouped,
          /*Fills, tertiary*/
          contentColor = MedTrackerTheme.colors.primaryLabel,
          disabledContainerColor = Color(0x1f787880),
          disabledContentColor = Color(0x4d3c3c43),
        )

      HIGButtonStyle.Borderless ->
        ButtonDefaults.elevatedButtonColors(
          contentColor = MedTrackerTheme.colors.primary400,
          disabledContentColor = Color(0x4d3c3c43),
        )
    }

  ElevatedButton(
    onClick = onClick,
    enabled = enabled,
    shape = RoundedCornerShape(dimensionResource(R.dimen.list_corner_radius_default)),
    colors = buttonColors,
    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
  ) {
    Text(
      text = text,
      style = MedTrackerTheme.typography.body,
      modifier = Modifier.padding(vertical = 8.dp),
    )

    Spacer(modifier = Modifier.weight(1f))

    Icon(
      imageVector = icon,
      contentDescription = "Show options",
      tint = MedTrackerTheme.colors.secondaryLabel,
      modifier = Modifier.size(16.dp),
    )
  }
}

// Region rework
@Composable
fun FlyTextButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  content: @Composable RowScope.() -> Unit,
) {
  TextButton(
    onClick,
    modifier,
    enabled,
    colors = ButtonDefaults.textButtonColors(contentColor = colors.primary600),
    content = { ProvideTextStyle(value = MedTrackerTheme.typography.body) { content() } },
  )
}

@Composable
fun FlyElevatedButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  shape: Shape = ButtonShape,
  content: @Composable RowScope.() -> Unit,
) {
  ElevatedButton(
    onClick,
    modifier,
    enabled,
    shape = shape,
    colors =
      ButtonDefaults.elevatedButtonColors(
        containerColor = MedTrackerTheme.colors.tertiaryFill,
        contentColor = MedTrackerTheme.colors.primary600,
        disabledContainerColor = MedTrackerTheme.colors.tertiaryFill,
        disabledContentColor = MedTrackerTheme.colors.tertiaryLabel,
      ),
    content = { ProvideTextStyle(value = MedTrackerTheme.typography.body) { content() } },
  )
}

@Composable
fun FlyButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  shape: Shape = ButtonShape,
  content: @Composable RowScope.() -> Unit,
) {
  Button(
    onClick,
    modifier,
    enabled,
    shape = shape,
    colors =
      ButtonDefaults.buttonColors(
        containerColor = MedTrackerTheme.colors.primary400,
        contentColor = MedTrackerTheme.colors.primaryLabelDark,
        disabledContainerColor = MedTrackerTheme.colors.tertiaryFill,
        disabledContentColor = MedTrackerTheme.colors.tertiaryLabel,
      ),
    content = { ProvideTextStyle(value = MedTrackerTheme.typography.body) { content() } },
  )
}

@Composable
fun FlyTonalButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  shape: Shape = ButtonShape,
  content: @Composable RowScope.() -> Unit,
) {
  Button(
    onClick,
    modifier,
    enabled,
    shape = shape,
    colors =
      ButtonDefaults.filledTonalButtonColors(
        containerColor = MedTrackerTheme.colors.primaryTinted,
        contentColor = MedTrackerTheme.colors.primary600,
        disabledContainerColor = MedTrackerTheme.colors.tertiaryFill,
        disabledContentColor = MedTrackerTheme.colors.tertiaryLabel,
      ),
    content = { ProvideTextStyle(value = MedTrackerTheme.typography.body) { content() } },
  )
}

// Endregion

@Preview(
  name = "Buttons",
  showBackground = false,
  showSystemUi = true,
  device = "spec:parent=pixel_8,navigation=buttons",
)
@Composable
private fun PreviewButtons() {
  MedTrackerTheme {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Top,
    ) {
      Row {
        HIGListButton(
          stringResource(R.string.button_default_text),
          {},
          enabled = true,
          style = HIGButtonStyle.Bezeled,
          // style = HIGButtonStyle.Filled
        )
      }

      Row {
        HIGButton(
          stringResource(R.string.button_default_text),
          {},
          enabled = true,
          style = HIGButtonStyle.Filled,
        )
        HIGButton(
          stringResource(R.string.button_default_text),
          {},
          enabled = false,
          style = HIGButtonStyle.Filled,
        )
      }

      Row {
        HIGButton(
          stringResource(R.string.button_default_text),
          {},
          enabled = true,
          style = HIGButtonStyle.Bezeled,
        )

        HIGButton(
          stringResource(R.string.button_default_text),
          {},
          enabled = false,
          style = HIGButtonStyle.Bezeled,
        )
      }
      Row {
        HIGButton(
          stringResource(R.string.button_default_text),
          {},
          enabled = true,
          style = HIGButtonStyle.Borderless,
        )

        HIGButton(
          stringResource(R.string.button_default_text),
          {},
          enabled = false,
          style = HIGButtonStyle.Borderless,
        )
      }
    }
  }
}
