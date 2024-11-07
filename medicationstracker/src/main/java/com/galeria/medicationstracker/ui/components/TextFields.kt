package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
  supportingText: String? = null,
  placeholder: String? = null,
  enabled: Boolean = true,
  isError: Boolean = false,
  errorMessage: String? = null,
  modifier: Modifier = Modifier,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {
  TextField(
    value = value,
    singleLine = true,
    isError = isError,
    textStyle = MedTrackerTheme.typography.body,
    onValueChange = onValueChange,
    placeholder = { placeholder?.let { Text(it) } },
    label = {
      Text(
        if (isError) {
          errorMessage!!
        } else {
          label
        }
      )
    },
    supportingText = { supportingText?.let { Text(it) } },
    enabled = enabled,
    keyboardOptions = keyboardOptions,
    visualTransformation = visualTransformation,
    modifier = modifier,
    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
    colors =
      TextFieldDefaults.colors(
        unfocusedContainerColor = MedTrackerTheme.colors.secondaryBackground,
        unfocusedIndicatorColor = MedTrackerTheme.colors.sysTransparent,
        disabledIndicatorColor = MedTrackerTheme.colors.sysTransparent,
        focusedIndicatorColor = MedTrackerTheme.colors.primary400,
        focusedContainerColor = MedTrackerTheme.colors.secondaryBackground,
        errorIndicatorColor = MedTrackerTheme.colors.sysError,
      ),
  )
}

// TODO.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOutlinedTextField(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  label: String? = null,
  placeholder: String? = null,
  leadingIcon: @Composable (() -> Unit)? = null,
  trailingIcon: @Composable (() -> Unit)? = null,
  isError: Boolean = false,
  errorMessage: String? = null,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  singleLine: Boolean = false,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    label = { label?.let { Text(it, color = MedTrackerTheme.colors.primaryLabel) } },
    placeholder = { placeholder?.let { Text(it, color = MedTrackerTheme.colors.secondaryLabel) } },
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    isError = isError,
    enabled = enabled,
    readOnly = readOnly,
    singleLine = singleLine,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    colors =
      TextFieldDefaults.colors(
        focusedTextColor = MedTrackerTheme.colors.primaryLabel,
        unfocusedTextColor = MedTrackerTheme.colors.primaryLabel,
        disabledTextColor = MedTrackerTheme.colors.tertiaryLabel,
        errorTextColor = MedTrackerTheme.colors.primaryLabel,
        //
        focusedContainerColor = MedTrackerTheme.colors.sysTransparent,
        unfocusedContainerColor = MedTrackerTheme.colors.sysTransparent,
        disabledContainerColor = MedTrackerTheme.colors.sysTransparent,
        errorContainerColor = MedTrackerTheme.colors.sysTransparent,
        //
        unfocusedIndicatorColor = MedTrackerTheme.colors.sysTransparent,
        disabledIndicatorColor = MedTrackerTheme.colors.sysTransparent,
        errorIndicatorColor = MedTrackerTheme.colors.sysTransparent,
      ),
  )
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
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        /*         MyTextField(
          value = "value",
          onValueChange = {},
          label = "label",
          enabled = false,
          modifier = TODO(),
          visualTransformation = TODO(),
          keyboardOptions = TODO(),
        )
        MyTextField(
          value = "value",
          onValueChange = {},
          label = "label",
          enabled = true,
          modifier = TODO(),
          visualTransformation = TODO(),
          keyboardOptions = TODO(),
        ) */
        Spacer(modifier = Modifier.height(24.dp))

        MyRadioButton(selected = true, onClick = {})
        MyCheckbox(checked = true, {})
        MyCheckbox(checked = false, {})

        MyCheckbox(checked = true, {}, enabled = false)
        MyCheckbox(checked = false, {}, enabled = false)
      }
    }
  }
}
