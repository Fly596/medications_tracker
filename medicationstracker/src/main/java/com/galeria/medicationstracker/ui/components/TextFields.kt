package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun FlyTextField(
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
  BasicTextField(
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 8.dp, bottom = 16.dp),
    value = value,
    onValueChange = onValueChange,
    singleLine = true,
    keyboardOptions = keyboardOptions,
    textStyle = MedTrackerTheme.typography.body,
    decorationBox = { innerTextField ->
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(MedTrackerTheme.colors.primaryBackground, RoundedCornerShape(6.dp))
          .padding(16.dp),
        contentAlignment = Alignment.BottomStart
      ) {
        if (value.isEmpty()) {
          Text("Add Medication Name", color = Color.LightGray)
        }
        innerTextField()
      }

    }
  )
}

@Composable
fun MyTextField(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  isPrimaryColor: Boolean = true,
  supportingText: String? = null,
  placeholder: String? = null,
  enabled: Boolean = true,
  isError: Boolean = false,
  errorMessage: String? = null,
  readOnly: Boolean = false,
  modifier: Modifier = Modifier,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {
  TextField(
    value = value,
    singleLine = true,
    readOnly = readOnly,
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
    shape = RoundedCornerShape(
      topStart = 12.dp,
      topEnd = 12.dp,
      bottomEnd = 12.dp,
      bottomStart = 12.dp
    ),
    colors =
      TextFieldDefaults.colors(
        unfocusedContainerColor =
          if (isPrimaryColor) {
            MedTrackerTheme.colors.primaryBackground
          } else {
            MedTrackerTheme.colors.secondaryBackground

          },
        unfocusedIndicatorColor = MedTrackerTheme.colors.sysTransparent,
        disabledIndicatorColor = MedTrackerTheme.colors.sysTransparent,
        focusedIndicatorColor = MedTrackerTheme.colors.primary400,
        focusedContainerColor = MedTrackerTheme.colors.primaryBackground,
        errorIndicatorColor = MedTrackerTheme.colors.sysError,
        focusedLabelColor = MedTrackerTheme.colors.primary400,
        unfocusedLabelColor = MedTrackerTheme.colors.secondaryLabel,
        errorLabelColor = MedTrackerTheme.colors.sysError,
        errorSupportingTextColor = MedTrackerTheme.colors.sysError,
        errorCursorColor = MedTrackerTheme.colors.sysError,
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

/*
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
        */
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
        ) *//*

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
*/

@Preview(
  showBackground = false,
  showSystemUi = false,
  device = "spec:parent=pixel_8,navigation=buttons",
)
@Composable
fun FlyTextFieldPreview() {
  FlyTextField(
    value = "",
    onValueChange = {},
    label = "Label",
    supportingText = "Supporting Text",
    placeholder = "Placeholder",
    enabled = true,
    isError = false,
    errorMessage = null,
    modifier = Modifier,
    visualTransformation = VisualTransformation.None,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
  )
}