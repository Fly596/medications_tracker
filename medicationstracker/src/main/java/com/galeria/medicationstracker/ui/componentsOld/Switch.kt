package com.galeria.medicationstracker.ui.componentsOld

import androidx.compose.foundation.interaction.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import com.galeria.medicationstracker.ui.theme.*

@Composable
fun MySwitch(
  checked: Boolean,
  onCheckedChange: ((Boolean) -> Unit)?,
  modifier: Modifier = Modifier,
  thumbContent: @Composable() (() -> Unit)? = null,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
  Switch(
    checked,
    onCheckedChange,
    modifier,
    thumbContent,
    enabled,
    colors =
      SwitchDefaults.colors(
        checkedThumbColor = MedTrackerTheme.colors.sysWhite,
        checkedTrackColor = MedTrackerTheme.colors.secondary400,
        checkedBorderColor = MedTrackerTheme.colors.sysTransparent,
        uncheckedBorderColor = MedTrackerTheme.colors.sysTransparent,
        uncheckedThumbColor = MedTrackerTheme.colors.sysWhite,
        uncheckedTrackColor = MedTrackerTheme.colors.secondaryFill,
      ),
    interactionSource,
  )
}
