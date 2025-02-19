package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.galeria.medicationstracker.ui.theme.GAppTheme
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme.colors

@Composable
fun GTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPrimaryColor: Boolean = false,
    supportingText: String? = null,
    placeholder: String? = null,
    singleLine: Boolean = false,
    enabled: Boolean = true,
    textStyle: TextStyle = MedTrackerTheme.typography.bodyMedium,
    isError: Boolean = false,
    errorMessage: String? = null,
    readOnly: Boolean = false,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {
    TextField(
        value = value,
        minLines = minLines,
        singleLine = singleLine,
        readOnly = readOnly,
        isError = isError,
        textStyle = textStyle,
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
        shape = MedTrackerTheme.shapes.small,
        colors =
            TextFieldDefaults.colors(
                unfocusedContainerColor =
                    if (isPrimaryColor) {
                        colors.primaryBackground
                    } else {
                        colors.secondaryBackground
                        
                    },
                unfocusedIndicatorColor = colors.sysBlack,
                disabledIndicatorColor = colors.sysTransparent,
                focusedIndicatorColor = colors.sysBlack,
                focusedContainerColor = colors.primaryBackground,
                errorIndicatorColor = colors.sysError,
                focusedLabelColor = colors.sysBlack,
                unfocusedLabelColor = colors.primaryLabel,
                errorLabelColor = colors.sysError,
                errorSupportingTextColor = colors.sysError,
                errorCursorColor = colors.sysError,
            ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun GTFPreview() {
    GAppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            GTextField(
                value = "value",
                onValueChange = {},
                label = "label"
            )
        }
    }
}