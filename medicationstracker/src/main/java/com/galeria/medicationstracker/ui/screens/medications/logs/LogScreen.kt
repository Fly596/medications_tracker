package com.galeria.medicationstracker.ui.screens.medications.logs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.galeria.medicationstracker.ui.components.FlyElevatedCardDashboard
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogScreen(
  onGoBackClick: () -> Unit = {},
) {

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Log") },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MedTrackerTheme.colors.primaryBackground,
          titleContentColor = MedTrackerTheme.colors.primaryLabel,
          navigationIconContentColor = MedTrackerTheme.colors.primaryLabel
        ),
        navigationIcon = {
          IconButton(
            onClick = { onGoBackClick.invoke() }
          ) {
            Icon(
              imageVector = Icons.Filled.ArrowBackIosNew,
              contentDescription = null
            )
          }
        }
      )
    },
    containerColor = MedTrackerTheme.colors.secondaryBackground,
    content = {
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth()
          .padding(it)
      ) {

        item {
          FlyElevatedCardDashboard(

          )
        }

      }
    }
  )
}