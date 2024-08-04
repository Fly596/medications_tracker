package com.galeria.medicationstracker.ui.screens.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column {
        // Heading.
        Text(text = "Profile")


    }
}

@Composable
fun PfpWithName(modifier: Modifier = Modifier){
    Column {
        Image(
            painter = painterResource(id = R.drawable.img_1543),
            contentDescription = "pfp",
            contentScale = ContentScale.Crop,
            modifier = modifier.clip(CircleShape).size(64.dp)
        )
    }

}

@Preview(name = "ProfileScreen")
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen()
}