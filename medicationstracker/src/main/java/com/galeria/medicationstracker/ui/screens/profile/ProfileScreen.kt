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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.componentsOld.FlyTextButton
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
            .fillMaxSize()
            .padding(top = 16.dp),
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
                style = MedTrackerTheme.typography.title1
            )
            FlyTextButton(
                onClick = {
                    /* TODO: open health */
                },
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
                    style = MedTrackerTheme.typography.title3,
                    color = MedTrackerTheme.colors.primaryLabel
                )
                Text(
                    text = uiState.value.user?.login.toString(),
                    style = MedTrackerTheme.typography.bodyMedium,
                    color = MedTrackerTheme.colors.secondaryLabel
                )
                // age, weight, height.
                Row(modifier = Modifier) {
                    TextField(
                        value = uiState.value.age.toString(),
                        onValueChange = { viewModel.updateAge(it.toInt()) },
                        readOnly = true,
                        singleLine = true,
                    )
                    TextField(
                        value = uiState.value.height.toString(),
                        onValueChange = { viewModel.updateHeight(it.toFloat()) },
                        readOnly = true,
                        singleLine = true,
                    )
                    TextField(
                        value = uiState.value.weight.toString(),
                        onValueChange = { viewModel.updateWeight(it.toFloat()) },
                        readOnly = true,
                        singleLine = true,
                    )
                }
            }
        }
        // menu items.
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                TextButton(
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
                TextButton(
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

