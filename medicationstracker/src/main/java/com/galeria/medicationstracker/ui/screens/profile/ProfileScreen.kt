package com.galeria.medicationstracker.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.User
import com.galeria.medicationstracker.ui.components.FlySimpleCard
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

/**
 * Represents the user's profile screen.
 *
 * This screen displays the user's profile information, including their
 * profile picture, name, and email. It also provides options for
 * managing notifications and app settings, as well as a logout button.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param onDoctorClick A callback function that is invoked when the user clicks on the settings option.
 * @param onNotificationsClick A callback function that is invoked when the user clicks on the notifications option.
 * @param viewModel The ViewModel for this screen. Defaults to a new instance of ProfileVM.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onDoctorClick: () -> Unit = {},
    onWeightClick: () -> Unit = {},
    onHeightClick: () -> Unit = {},
    viewModel: ProfileVM,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val doctorsList = uiState.doctors

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        // Display header with profile picture and name.
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
        ) {

            // User's profile information.
            PfpWithName(
                // TODO: get from firebase.
                painter = R.drawable.img_1543,
                userName = uiState.user?.name.toString(),
                userEmail = uiState.user?.login.toString()
            )
            Spacer(modifier = Modifier.weight(1f))

        }

        // HealthCardsGrid()

        // секция со списком врачей пользователя (или просто всех врачей).
        MyDoctorsList(
            listData = doctorsList,
            onDoctorClick = onDoctorClick,
            onClick = { viewModel.updateSelectedDoctor(it) }
        )
    }
}

@Composable
fun MyDoctorsList(
    modifier: Modifier = Modifier,
    listData: List<User> = listOf(),
    onDoctorClick: () -> Unit = {},
    onClick: (User?) -> Unit = {}
) {
    Column(modifier = modifier.padding(top = 8.dp)) {
        Text(
            text = "My Doctors",
            style = MedTrackerTheme.typography.title2
        )

        LazyColumn(modifier = modifier.fillMaxWidth()) {
            items(listData) { user ->
                // UserListCard(user.name.toString())
                DocListCard(
                    name = user.name.toString(),
                    speciality = user.type.toString(),
                    modifier = Modifier.clickable {
                        onClick(user)
                        onDoctorClick.invoke()
                    },
                )

            }
        }

    }

}

@Composable
fun DocListCard(
    modifier: Modifier = Modifier,
    name: String = "James",
    speciality: String = "Boobs Watcher",
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.doc_pfp_girl),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(56.dp),
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            Text(
                text = name,
                style = MedTrackerTheme.typography.title3
            )
            Text(
                text = speciality,
                style = MedTrackerTheme.typography.footnote
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {/* show doctor */ }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "more",
                tint = MedTrackerTheme.colors.secondaryLabel
            )
        }
    }
}

@Composable
fun HealthCardsGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            HealthCard(
                headText = "Weight",
                valueText = "145",
                unitsText = "lbs",
            )
        }
        item {
            HealthCard(
                headText = "Height",
                valueText = "6.5",
                unitsText = "ft",
                textColor = MedTrackerTheme.colors.primary400
            )
        }
    }
}

@Composable
fun HealthCard(
    headText: String = "Blood Pressure",
    valueText: String = "150/100",
    unitsText: String = "mmHg",
    textColor: Color = MedTrackerTheme.colors.sysError,
) {
    FlySimpleCard(modifier = Modifier) {
        Column(
            modifier = Modifier.padding(bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = headText,
                    style = MedTrackerTheme.typography.bodyEmphasized,
                    color = textColor
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Filled.ArrowForwardIos,
                        contentDescription = "more",
                        tint = MedTrackerTheme.colors.secondaryLabel
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = valueText,
                    style = MedTrackerTheme.typography.title1Emphasized
                )
                Text(
                    text = unitsText,
                    style = MedTrackerTheme.typography.footnote
                )

            }
        }
    }
}

@Composable
fun PfpWithName(
    painter: Int,
    userName: String,
    userEmail: String,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            painter = painterResource(painter),
            contentDescription = "pfp",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)
                .size(128.dp),
        )

        // Spacer(modifier = Modifier.width(32.dp)) // add default dp values.

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = userName,
                style = MedTrackerTheme.typography.title2Emphasized,
                color = MedTrackerTheme.colors.primaryLabel
            )
            Text(
                text = userEmail,
                style = MedTrackerTheme.typography.headline,
                color = MedTrackerTheme.colors.tertiaryLabel
            )
        }
        IconButton(
            onClick = {/*TODO: open settings*/ }
        ) {

            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Filled.Edit,
                contentDescription = "Settings",
                tint = MedTrackerTheme.colors.secondaryLabel
            )
        }
    }
}

@Composable
fun ProfileOptionItem(
    title: String,
    onClick: () -> Unit
) {

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                // .background(color =
                // MedicationsTrackerAppTheme.systemColors.backgroundLightSecondary)
                .clickable(onClick = onClick),
    ) {
    }
}
