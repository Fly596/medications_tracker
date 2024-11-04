package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun FlyElevatedCard(
  modifier: Modifier = Modifier,
  elevation: CardElevation = CardDefaults.elevatedCardElevation(),
  content: @Composable (ColumnScope.() -> Unit),
) {
  ElevatedCard(
    modifier = modifier,
    shape = CardDefaults.elevatedShape,
    colors =
      CardDefaults.elevatedCardColors(
        containerColor = MedTrackerTheme.colors.primaryBackgroundGrouped,
        contentColor = MedTrackerTheme.colors.primaryLabel,
      ),
    elevation = elevation,
    content = content,
  )
}
