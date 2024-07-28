package com.example.speechrecognitionapp.ui.shared.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.speechrecognitionapp.R
import com.example.speechrecognitionapp.ui.theme.SpeechRecognitionAppTheme

@Composable
fun ButtonComponent(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        enabled = true,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = SpeechRecognitionAppTheme.colors.extBlue.defaultLight,
            contentColor = Color.White,
            disabledContentColor = Color(0x1f787880),
            disabledContainerColor = Color(0x4d3c3c43)
        ),
        /*        elevation = ButtonDefaults.buttonElevation(
                   defaultElevation = 2.dp,
                   pressedElevation = 6.dp,
                   focusedElevation = 4.dp,
                   hoveredElevation = 4.dp,
                   disabledElevation = 0.dp
               ), */
        modifier = modifier,
        onClick = onClick,
    ) {

        ButtonText(text, MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun SecondaryButtonComponent(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x26007aff),
            contentColor = SpeechRecognitionAppTheme.colors.extBlue.defaultLight,
            disabledContentColor = Color(0x1f787880),
            disabledContainerColor = Color(0x4d3c3c43)
        ),

        modifier = modifier,
        onClick = { onClick.invoke() },

        ) {

        ButtonText(text, MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun ElevatedButtonComponent(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.elevatedButtonColors(
            contentColor = SpeechRecognitionAppTheme.colors.extBlue.defaultLight,
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
        ),
        modifier = modifier,
        onClick = { onClick.invoke() },
        enabled = false
    ) {
        ButtonText(text, MaterialTheme.typography.bodySmall)
    }
}


@Preview(
    name = "MedsScreen",
    device = "spec:width=1080px,height=2400px,navigation=buttons",
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewMedsScreen() {
    SpeechRecognitionAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonComponent(R.string.button_default_text, onClick = {})
            SecondaryButtonComponent(
                R.string.button_default_text,
                onClick = { }
            )
            ElevatedButtonComponent(R.string.button_default_text, onClick = {})

        }
    }
}


@Composable
private fun ButtonText(@StringRes text: Int, style: TextStyle) {
    Text(
        text = stringResource(id = text),
        style = style,
    )
}
