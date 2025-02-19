package com.galeria.medicationstracker.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun TestProfile(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FlyIconButton(size = 20.dp)

        Text(text = "Test", style = MedTrackerTheme.typography.bodyMedium)
    }

}

@Composable
private fun FlyIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    size: Dp = 24.dp,
    @DrawableRes icon: Int = R.drawable.male_symbol
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = { onClick.invoke() })
            .background(MedTrackerTheme.colors.primaryBackgroundGrouped)
            .padding(8.dp)
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painterResource(icon),
            contentDescription = null,
            tint = MedTrackerTheme.colors.primary400,
            modifier = Modifier
        )
    }
}

@Composable
private fun FlyIconButtonWithText(
    modifier: Modifier = Modifier,
    text: String = "Fuck Me",
    onClick: () -> Unit = {},
    size: Dp = 24.dp,
    @DrawableRes icon: Int = R.drawable.male_symbol
) {
    Row(
        modifier = modifier
            .clickable(onClick = { onClick.invoke() })
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FlyIconButton(onClick = onClick, size = size, icon = icon)
        Text(text = text, style = MedTrackerTheme.typography.bodyMedium)
    }

}

@Preview(name = "Test")
@Composable
private fun PreviewTest() {
    MedTrackerTheme {
        FlyIconButtonWithText()
    }
}