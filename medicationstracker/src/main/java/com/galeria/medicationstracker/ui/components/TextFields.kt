package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun MyTextField(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  modifier: Modifier = Modifier,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {
  TextField(
    value = value,
    textStyle = MedTrackerTheme.typography.body,
    onValueChange = onValueChange,
    label = { Text(label) },
    keyboardOptions = keyboardOptions,
    visualTransformation = visualTransformation,
    modifier = modifier,
    shape = RoundedCornerShape(12.dp),
    colors =
      TextFieldDefaults.colors(
        unfocusedContainerColor = MedTrackerTheme.colors.secondaryBackground,
        unfocusedIndicatorColor = MedTrackerTheme.colors.sysTransparent,
        disabledIndicatorColor = MedTrackerTheme.colors.sysTransparent,
        focusedIndicatorColor = MedTrackerTheme.colors.primary400,
        errorIndicatorColor = MedTrackerTheme.colors.sysTransparent,
        focusedContainerColor = MedTrackerTheme.colors.secondaryBackground,
      ),
  )
}

@Composable
fun MyText(text: String, modifier: Modifier = Modifier) {
  Text(text = text, style = MedTrackerTheme.typography.body, modifier = modifier)
}

@Preview(
  name = "TFields",
  showBackground = false,
  showSystemUi = true,
  device = "spec:parent=pixel_8,navigation=buttons",
)
@Composable
private fun PreviewButtons() {
  MedTrackerTheme {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Row { MyTextField(value = "value", onValueChange = {}, label = "label") }
    }
  }
}
