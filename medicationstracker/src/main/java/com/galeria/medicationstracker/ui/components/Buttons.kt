package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.TextStyle
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
  textStyle: TextStyle = MedTrackerTheme.typography.body,
  icon: ImageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
  leftIcon: ImageVector? = null,
  rightText: String? = null
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
          containerColor = MedTrackerTheme.colors.primaryBackground,
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
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp),
  ) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {

      if (leftIcon != null) {
        Icon(
          imageVector = leftIcon,
          contentDescription = null,
          tint = MedTrackerTheme.colors.primaryLabel,
          modifier = Modifier.size(24.dp),

          )
      }

      Text(
        text = text,
        style = textStyle,
        modifier = Modifier.padding(vertical = 8.dp),
      )

      Spacer(modifier = Modifier.weight(1f))

      Text(
        text = rightText ?: "",
        style = MedTrackerTheme.typography.body,
        modifier = Modifier.padding(vertical = 8.dp),
      )
      Spacer(modifier = Modifier.width(8.dp))
      Icon(
        imageVector = icon,
        contentDescription = "Show options",
        tint = MedTrackerTheme.colors.secondaryLabel,
        modifier = Modifier.size(16.dp),
      )
    }

  }
}

// Region rework
@Composable
fun FlyTextButton(
  onClick: () -> Unit,
  errorButton: Boolean = false,
  modifier: Modifier = Modifier,
  textStyle: TextStyle = MedTrackerTheme.typography.headline,
  enabled: Boolean = true,
  content: @Composable RowScope.() -> Unit,
) {
  TextButton(
    onClick,
    modifier,
    enabled,
    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    colors = if (errorButton) {
      ButtonDefaults.textButtonColors(contentColor = colors.sysError)
    } else {
      ButtonDefaults.textButtonColors(contentColor = colors.primary600)
    },
    content = { ProvideTextStyle(value = textStyle) { content() } },
  )
}

@Composable
fun FlyElevatedButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  textStyle: TextStyle = MedTrackerTheme.typography.headline,
  shape: Shape = ButtonShape,
  content: @Composable RowScope.() -> Unit,
) {
  ElevatedButton(
    onClick,
    modifier,
    enabled,
    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    shape = shape,
    colors =
      ButtonDefaults.elevatedButtonColors(
        containerColor = colors.tertiaryFill,
        contentColor = colors.primary600,
        disabledContainerColor = colors.tertiaryFill,
        disabledContentColor = colors.tertiaryLabel,
      ),
    content = { ProvideTextStyle(value = textStyle) { content() } },
  )
}

@Composable
fun FlyButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  textStyle: TextStyle = MedTrackerTheme.typography.headline,
  shape: Shape = ButtonShape,
  content: @Composable RowScope.() -> Unit,
) {
  Button(
    onClick,
    modifier,
    enabled,
    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    shape = shape,
    colors =
      ButtonDefaults.buttonColors(
        containerColor = colors.primary400,
        contentColor = colors.primaryLabelDark,
        disabledContainerColor = colors.tertiaryFill,
        disabledContentColor = colors.tertiaryLabel,
      ),
    content = { ProvideTextStyle(value = textStyle) { content() } },
  )
}

@Composable
fun FlyErrorButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  textStyle: TextStyle = MedTrackerTheme.typography.headline,
  shape: Shape = ButtonShape,
  content: @Composable RowScope.() -> Unit,
) {
  Button(
    onClick,
    modifier,
    enabled,
    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    shape = shape,
    colors =
      ButtonDefaults.buttonColors(
        containerColor = colors.sysError,
        contentColor = colors.primaryLabelDark,
        disabledContainerColor = colors.tertiaryFill,
        disabledContentColor = colors.tertiaryLabel,
      ),
    content = { ProvideTextStyle(value = textStyle) { content() } },
  )
}

@Composable
fun FlyTonalButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  textStyle: TextStyle = MedTrackerTheme.typography.headline,
  shape: Shape = ButtonShape,
  content: @Composable RowScope.() -> Unit,
) {
  Button(
    onClick,
    modifier,
    enabled,
    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    shape = shape,
    colors =
      ButtonDefaults.filledTonalButtonColors(
        containerColor = colors.primaryTinted,
        contentColor = colors.primary600,
        disabledContainerColor = colors.tertiaryFill,
        disabledContentColor = colors.tertiaryLabel,
      ),
    content = { ProvideTextStyle(value = textStyle) { content() } },
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
      verticalArrangement = Arrangement.Center,
    ) {
      Row {
        FlyButton(onClick = {}, enabled = true) { Text(text = "Sign In") }
        FlyTextButton(onClick = {}, enabled = true) { Text(text = "Sign In") }
        // FlyElevatedButton(onClick = {}, enabled = true) { Text(text = "Sign In") }
        // FlyTonalButton(onClick = {}, enabled = true) { Text(text = "Sign In") }
        HIGListButton("text", onClick = {}, enabled = true)
      }
    }
  }
}
