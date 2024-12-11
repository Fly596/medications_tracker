package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.ui.screens.medications.ModalDatePicker
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlyTopAppBar(
  title: String = "Title",
  modifier: Modifier = Modifier,
  navigationIcon: @Composable () -> Unit = {},
  actions: @Composable RowScope.() -> Unit = {},
  expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
  windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
  colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
  textStyle: TextStyle = MedTrackerTheme.typography.largeTitleEmphasized
) {
  Column(
    modifier = Modifier.background(
      color = MedTrackerTheme.colors.primaryBackground
    ).fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(text = title, style = MedTrackerTheme.typography.largeTitleEmphasized)
    }
    //Spacer(modifier = Modifier.padding(vertical = 8.dp))
    HorizontalDivider(
      color = MedTrackerTheme.colors.opaqueSeparator,
      modifier = Modifier.layout() { measurable, constraints ->
        val placeable = measurable.measure(
          constraints.copy(
            maxWidth = constraints.maxWidth + 16.dp.roundToPx(),
          )
        )
        layout(placeable.width, placeable.height) {
          placeable.place(8.dp.roundToPx(), 0)
        }
      }
    )

  }

}
