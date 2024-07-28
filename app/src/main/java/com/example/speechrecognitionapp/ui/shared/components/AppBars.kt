package com.example.speechrecognitionapp.ui.shared.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speechrecognitionapp.R
import com.example.speechrecognitionapp.ui.theme.SpeechRecognitionAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    @StringRes title: Int,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        navigationIcon = {
            ProfileIcon(onProfileClick)
        },
        title = {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
            ) {
                Text(
                    stringResource(R.string.username),
                    style = MaterialTheme.typography.headlineMedium
                )

                NavigationRow(onProfileClick, "Profile")
            }

        },
        modifier = modifier.fillMaxWidth()
    )
}


@Composable
private fun ProfileIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.aestetic),
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar() {
    CenterAlignedTopAppBar(
        navigationIcon = {
            TextButton(
                modifier = Modifier.padding(),
                onClick = {}
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 11.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Label",
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }
        },
        title = {
            Text("Title", style = MaterialTheme.typography.headlineMedium)

        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.AccountBox,
                    contentDescription = null,
                    //    modifier = Modifier.size(20.dp)
                )
            }
        }
    )
}

@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    SpeechRecognitionAppTheme {
        NavBar()
    }
}