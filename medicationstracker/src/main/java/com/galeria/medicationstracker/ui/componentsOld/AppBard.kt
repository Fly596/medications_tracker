package com.galeria.medicationstracker.ui.componentsOld

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.galeria.medicationstracker.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlyTopAppBar(
    title: String = "Title",
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    icon: ImageVector? = null,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    textStyle: TextStyle = MedTrackerTheme.typography.title1Emphasized
) {
    Column(
        modifier = Modifier
            .background(
                color = MedTrackerTheme.colors.primaryBackground
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 0.dp),
            verticalAlignment = Alignment.Top,

            ) {
            Text(text = title, style = textStyle)

            // Spacer(modifier = Modifier.weight(1f))

            if (icon != null) {
                IconButton(
                    onClick = { onClick.invoke() },
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(28.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = icon,
                        contentDescription = null,
                    )
                }
            }

        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        HorizontalDivider(
            color = MedTrackerTheme.colors.opaqueSeparator,
            /*       modifier = Modifier.layout() { measurable, constraints ->
                    val placeable = measurable.measure(
                      constraints.copy(
                        maxWidth = constraints.maxWidth + 16.dp.roundToPx(),
                      )
                    )
                    layout(placeable.width, placeable.height) {
                      placeable.place(8.dp.roundToPx(), 0)
                    }
                  } */
        )

    }

}
