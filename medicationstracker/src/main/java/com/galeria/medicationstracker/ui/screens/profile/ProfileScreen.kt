package com.galeria.medicationstracker.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.GTextButton
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onWeightClick: () -> Unit = {},
    onHeightClick: () -> Unit = {},
    viewModel: ProfileVM,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // title and "edit" button.
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Profile",
                style = MedTrackerTheme.typography.display3
            )
            GTextButton(
                onClick = {
                    /* TODO: open health */
                },
                textStyle = MedTrackerTheme.typography.bodyLarge,
            ) {
                Text(text = "Edit")
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = MedTrackerTheme.colors.separator,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        // pfp, name, email.
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.img_1543),
                contentDescription = "pfp",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(CircleShape)
                    .size(108.dp),
            )
            // name, login.
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = uiState.value.user?.name.toString(),
                    style = MedTrackerTheme.typography.title1,
                    color = MedTrackerTheme.colors.primaryLabel
                )
                Text(
                    text = uiState.value.user?.login.toString(),
                    style = MedTrackerTheme.typography.title3,
                    color = MedTrackerTheme.colors.primaryLabel
                )
                // age, weight, height.
                Column(modifier = Modifier) {
                    Text(
                        text = "${uiState.value.age} yr",
                        style = MedTrackerTheme.typography.bodyMediumEmphasized,
                        color = MedTrackerTheme.colors.secondaryLabel
                    )
                    Text(
                        text = "${uiState.value.height} cm",
                        style = MedTrackerTheme.typography.bodyMediumEmphasized,
                        color = MedTrackerTheme.colors.secondaryLabel
                    )
                    Text(
                        text = "${uiState.value.weight} kg",
                        style = MedTrackerTheme.typography.bodyMediumEmphasized,
                        color = MedTrackerTheme.colors.secondaryLabel
                    )
                }
            }
        }
        // menu items.
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                GTextButton(
                    modifier = Modifier,
                    onClick = {
                        // TODO: history page
                    }
                ) {
                    Text(
                        text = "History",
                        style = MedTrackerTheme.typography.bodyLargeEmphasized
                    )
                }
            }
            item {
                GTextButton(
                    modifier = Modifier,
                    onClick = {
                        // TODO: medications page
                    }
                ) {
                    Text(
                        text = "Medications",
                        style = MedTrackerTheme.typography.bodyLargeEmphasized
                    )
                }
            }
        }
        // TODO: pages.
    }
}

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    ) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AccountScreenHead()
    }
    
}

@Composable
fun AccountScreenHead(
    modifier: Modifier = Modifier,
    
) {
    Row(modifier = modifier){
        Text(
            text = "My Account"
        )
    }
}

@Composable
@Preview
fun AccScreenPreview() {
    MedTrackerTheme {
        AccountScreen()
    }
}