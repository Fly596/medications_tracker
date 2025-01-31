package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.galeria.medicationstracker.ui.theme.*
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme.colors


@Composable
fun GPrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = GappTheme.typography.labelLarge,
    contentPaddings: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 8.dp
    ),
    shape: Shape = GappTheme.shapes.small,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick,
        modifier,
        enabled,
        contentPadding = contentPaddings,
        shape = shape,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = colors.primary400,
                contentColor = colors.primaryLabelDark,
                disabledContainerColor = colors.secondaryFill,
                disabledContentColor = colors.tertiaryLabel,
            ),
        content = { ProvideTextStyle(value = textStyle) { content() } },
    )
}

@Composable
fun GSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = GappTheme.typography.labelLarge,
    contentPaddings: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 8.dp
    ),
    shape: Shape = GappTheme.shapes.small,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick,
        modifier,
        enabled,
        contentPadding = contentPaddings,
        shape = shape,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = colors.secondary400,
                contentColor = colors.primaryLabelDark,
                disabledContainerColor = colors.secondaryFill,
                disabledContentColor = colors.tertiaryLabel,
            ),
        content = { ProvideTextStyle(value = textStyle) { content() } },
    )
}

@Composable
fun GTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = GappTheme.typography.labelLarge,
    contentPaddings: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 8.dp
    ),
    content: @Composable RowScope.() -> Unit,
) {
    TextButton(
        onClick,
        modifier,
        enabled,
        contentPadding = contentPaddings,
        shape = GappTheme.shapes.small,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = colors.primary400,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = colors.tertiaryLabel,
            ),
        content = { ProvideTextStyle(value = textStyle) { content() } },
    )
}

@Composable
fun GOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = GappTheme.typography.labelLarge,
    contentPaddings: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 8.dp
    ),
    shape: Shape = GappTheme.shapes.small,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick,
        modifier,
        enabled,
        contentPadding = contentPaddings,
        shape = GappTheme.shapes.small,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colors.primaryLabel,
            disabledContentColor = colors.tertiaryLabel,
        ),
        border = BorderStroke(
            width = 0.5.dp,
            color = colors.secondaryLabel
        ),
        content = { ProvideTextStyle(value = textStyle) { content() } },
    )
}

@Composable
fun GTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = GappTheme.typography.labelLarge,
    contentPaddings: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 8.dp
    ),
    shape: Shape = GappTheme.shapes.small,
    content: @Composable RowScope.() -> Unit,
) {
    FilledTonalButton(
        onClick,
        modifier,
        enabled,
        contentPadding = contentPaddings,
        shape = shape,
        colors =
            ButtonDefaults.filledTonalButtonColors(
                containerColor = colors.primaryTinted,
                contentColor = colors.primary600,
                disabledContainerColor = colors.secondaryFill,
                disabledContentColor = colors.tertiaryLabel,
            ),
        content = { ProvideTextStyle(value = textStyle) { content() } },
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun GPrimaryButtonPreview() {
    GAppTheme {
        Column {
            Row {
                GPrimaryButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = true,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    },
                )
                GPrimaryButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = false,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    },
                )
            }
            Row {
                GSecondaryButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = true,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    },
                )
                GSecondaryButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = false,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    },
                )
            }
            Row {
                GTextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = true,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    },
                )
                GTextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = false,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    },
                )
            }
            Row {
                GOutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = true,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    }
                )
                GOutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = false,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    }
                )
            }
            Row {
                GTonalButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = true,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    }
                )
                GTonalButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp),
                    enabled = false,
                    textStyle = MedTrackerTheme.typography.bodyMedium,
                    content = {
                        androidx.compose.material3.Text("Button Text")
                    }
                )
            }
        }
    }
    
}
/* @Composable
fun GIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = GappTheme.typography.labelLarge,
    @DrawableRes icon: Int = R.drawable.male_symbol,
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = { onClick.invoke() })
            .size(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painterResource(icon),
            contentDescription = null,
            tint = MedTrackerTheme.colors.primary400,
        )
    }
} */