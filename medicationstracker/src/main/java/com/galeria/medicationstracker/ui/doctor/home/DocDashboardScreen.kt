package com.galeria.medicationstracker.ui.doctor.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.User
import com.galeria.medicationstracker.ui.components.FlySimpleCard
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.doctor.patients.PatientsListViewModel
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun DocDashboardScreen(
  modifier: Modifier = Modifier,
  viewModel: PatientsListViewModel = viewModel(),
  onPatientsClick: () -> Unit = {}
) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle()

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(32.dp)
  ) {
    HeaderSection(modifier = Modifier.fillMaxWidth())

    AppointmentsView(
      // onSeeAllClick = onPatientsClick,
      patientsList = uiState.value.patients
    )

    // AppointmentsStats()
  }

}

@Composable
fun AppointmentsView(
  modifier: Modifier = Modifier,
  onSeeAllClick: () -> Unit = {},
  patientsList: List<User> = emptyList()
) {
  Column(modifier = modifier.fillMaxWidth()) {

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {

      Text(
        text = "Upcoming Appointments",
        style = MedTrackerTheme.typography.headline
      )
      FlyTextButton(
        onClick = { onSeeAllClick.invoke() }
      ) {
        Text(
          text = "See All",
          style = MedTrackerTheme.typography.caption1Emphasized
        )
      }

    }
    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      items(patientsList) { patient ->
        AppointmentCard(
          name = patient.name.toString(),
          time = "7:00 pm - 7:30 pm",
          type = "Online Consultation"
        )

      }

      /*       item {
              AppointmentCard(
                name = "Courtney Henry",
                time = "7:00 pm - 7:30 pm",
                type = "Online Consultation",
                image = painterResource(id = R.drawable.montana_pfp)
              )
            }
            item {
              AppointmentCard(
                name = "Albert Flores",
                time = "9:00 pm - 9:30 pm",
                type = "In Person Visit"
              )

            }
            item {

              AppointmentCard(
                name = "Brianna Clark",
                time = "10:00 pm - 10:30 pm",
                type = "In Person Visit"
              )
            } */

    }

  }
}

@Composable
fun AppointmentCard(
  name: String,
  time: String,
  type: String,
  image: Painter = painterResource(R.drawable.default_pfp)
) {
  FlySimpleCard(
    modifier = Modifier
      .width(132.dp)
      .height(148.dp),
  ) {
    Column(
      modifier = Modifier,
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
          .size(36.dp)
          .clip(CircleShape)
      )
      Text(
        text = name,
        style = MedTrackerTheme.typography.caption1Emphasized
      )
      Text(
        text = time,
        style = MedTrackerTheme.typography.caption2
      )
      Text(
        text = type,
        style = MedTrackerTheme.typography.caption2
      )
    }
  }
}

@Composable
fun HeaderSection(
  modifier: Modifier = Modifier,
  image: Painter = painterResource(id = R.drawable.g_eazy_pfp)
) {

  Row(
    modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = image,
      contentDescription = null,
      modifier = Modifier
        .clip(CircleShape)
        .size(
          width = 64.dp,
          height = 64.dp
        )
    )

    Spacer(modifier = Modifier.width(16.dp))

    Column(
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Text(
        text = "Welcome Back!",
        style = MedTrackerTheme.typography.subhead
      )
      Text(
        "Gerald",
        style = MedTrackerTheme.typography.headline
      )
    }

    Spacer(modifier = Modifier.weight(1f))
    Row() {
      IconButton(
        onClick = {}
      ) {
        Icon(
          Icons.Outlined.Notifications,
          contentDescription = null
        )
      }
      IconButton(
        onClick = {}
      ) {
        Icon(
          Icons.AutoMirrored.Outlined.Message,
          contentDescription = null
        )
      }
    }
  }
}

@Preview(
  showBackground = true,
  backgroundColor = 0xFFf2f2f7,
  fontScale = 1.0f
)
@Composable
fun DocDashboardScreenPreview() {
  MedTrackerTheme {
    DocDashboardScreen()
  }
}

@Composable
fun AppointmentsStats() {
  Column(modifier = Modifier.fillMaxWidth()) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {

      Text(
        text = "Appointment Statistics",
        style = MedTrackerTheme.typography.headline
      )
      FlyTextButton(
        onClick = { /* TODO: See all appointments */ }
      ) {
        Text(
          text = "Last 12 Month",
          style = MedTrackerTheme.typography.caption1Emphasized
        )
      }

    }

    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      item {
        StatisticCard(
          label = "Total",
          value = "760",
          change = "+22%"
        )

      }
      item {
        StatisticCard(
          label = "Online",
          value = "494",
          change = "-12%"
        )

      }
      item {
        StatisticCard(
          label = "In Person",
          value = "266",
          change = "+30%"
        )

      }

    }
  }
}

@Composable
fun StatisticCard(
  label: String,
  value: String,
  change: String
) {
  FlySimpleCard(
    modifier = Modifier
      .width(132.dp)
      .height(96.dp),
  ) {
    Column(
      modifier = Modifier,
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Text(
        text = label,
        style = MedTrackerTheme.typography.body
      )

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Text(
          text = value,
          style = MedTrackerTheme.typography.title3Emphasized
        )
        Text(
          text = change,
          style = MedTrackerTheme.typography.caption1
        )
      }

    }
  }
}

/*
@Composable
fun DocDashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DocDashboardVM = viewModel()
) {
  val state = viewModel._docDashboardState.collectAsStateWithLifecycle()

  val testValues = state.value.tempList
  // val patientsList = state.value.patientsList
  val headerValues = state.value.headerValuesUsers

  Column(modifier = modifier.fillMaxWidth()) {

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
      // Header Row.
      item {
        FlyRow(values = headerValues, isHeader = true)
      }

      */
/*       items(patientsList!!.size) { index ->
              FlyRow(
                values = listOf<String>(patientsList[index].toString()),
                isHeader = false
              )

            } *//*


      // Body Rows.
      items(testValues.size) { index ->
        FlyRow(values = testValues[index], isHeader = false)
      }

    }
  }
}

@Composable
fun FlyRow(
    modifier: Modifier = Modifier,
    isHeader: Boolean = false,
    values: List<String>,
) {
  LazyRow(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Start,
  ) {
    items(values.size) { index ->
      FlyTableTextField(
        value = values[index],
        label = "",
        isHeader = isHeader,
        textStyles = MedTrackerTheme.typography.body,
        onValueChange = {},
        readOnly = true,
      )
    }
  }
}*/
