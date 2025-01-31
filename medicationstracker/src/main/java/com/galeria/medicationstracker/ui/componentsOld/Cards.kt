package com.galeria.medicationstracker.ui.componentsOld

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.unit.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.theme.*

@Composable
fun FlySimpleCard(
    modifier: Modifier = Modifier,
    isPrimaryBackground: Boolean = true,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = if (isPrimaryBackground) {
                    MedTrackerTheme.colors.primaryBackground
                } else {
                    MedTrackerTheme.colors.secondaryBackground
                },
                contentColor = MedTrackerTheme.colors.primaryLabel,
                disabledContainerColor = MedTrackerTheme.colors.primaryTinted,
                disabledContentColor = MedTrackerTheme.colors.secondary600
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            content.invoke(this)
        }
    }
}

@Composable
fun FlyElevatedCardDashboard(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String = "Medicine Name",
    time: String = "9:00 AM",
    info: String = "Mon, Tue, Fri...",
    medication: UserMedication? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(),
    // content: @Composable (ColumnScope.() -> Unit)
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 1.dp,
            pressedElevation = 8.dp,
            focusedElevation = 10.dp,
        ),
        colors =
            CardDefaults.elevatedCardColors(
                containerColor = MedTrackerTheme.colors.primaryBackground,
                contentColor = MedTrackerTheme.colors.primaryLabel,
                disabledContainerColor = MedTrackerTheme.colors.primaryTinted,
                disabledContentColor = MedTrackerTheme.colors.secondary600
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Android Icon",
                    modifier = Modifier.size(32.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxHeight(),
            ) {
                Text(title, style = MedTrackerTheme.typography.headline)
                Text(time, style = MedTrackerTheme.typography.bodyMedium)
                Text(info, style = MedTrackerTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.weight(1f))

            var isChecked by remember { mutableStateOf(false) } // State to track icon

            Column {
                IconButton(onClick = { isChecked = !isChecked }) {
                    Icon(
                        imageVector = if (isChecked) {
                            Icons.Filled.CheckCircle
                        } else {
                            Icons.Outlined.CheckCircle
                        },
                        contentDescription = "Android Icon",
                        modifier = Modifier.size(32.dp),
                        tint = if (isChecked) {
                            MedTrackerTheme.colors.primary400
                        } else {
                            MedTrackerTheme.colors.separator
                        }
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

            }

        }
    }
}

@Composable
fun FLySimpleCardContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors =
            CardDefaults.elevatedCardColors(
                containerColor = MedTrackerTheme.colors.secondaryBackgroundGrouped,
                contentColor = MedTrackerTheme.colors.primaryLabel,
                disabledContainerColor = MedTrackerTheme.colors.primaryTinted,
                disabledContentColor = MedTrackerTheme.colors.secondary600
            )
    ) {
        content()
    }
}

@Composable
fun NavigationRow(onClick: () -> Unit, label: String? = null) {
    Row(
        modifier = Modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        FlyTextButton(
            onClick
        ) {
            if (label != null) {
                Text(label)
            }
            /*       Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    tint = MedTrackerTheme.colors.secondary400,
                    modifier = Modifier.padding(start = 8.dp),
                  ) */
        }
    }
}
