package com.galeria.medicationstracker.ui.componentsOld

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.galeria.medicationstracker.ui.theme.*

@Composable
fun FlyTableTextField(
    modifier: Modifier = Modifier,
    value: String,
    isHeader: Boolean = false,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    isPrimaryColor: Boolean = false,
    supportingText: String? = null,
    textStyles: TextStyle = MedTrackerTheme.typography.bodyMedium,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {
    FlyTextField(
        modifier = modifier
            .width(150.dp),
        /*
              .height(32.dp) */
        value = value,
        singleLine = true,
        readOnly = readOnly,
        isError = isError,
        textStyle = if (isHeader) {
            MedTrackerTheme.typography.bodyMediumEmphasized
        } else {
            MedTrackerTheme.typography.bodySmall
        },
        onValueChange = onValueChange,
        placeholder = { placeholder?.let { Text(it) } },
        label = {
            Text(
                if (isError) {
                    errorMessage!!
                } else {
                    label
                },
                style = if (isHeader) {
                    MedTrackerTheme.typography.bodyMediumEmphasized
                } else {
                    MedTrackerTheme.typography.bodySmall
                }
            )
        },
        maxLines = 1,
        colors =
            TextFieldDefaults.colors(
                unfocusedContainerColor =
                    if (isPrimaryColor) {
                        MedTrackerTheme.colors.secondaryBackground
                    } else {
                        MedTrackerTheme.colors.primaryBackground
                    },
                unfocusedIndicatorColor = MedTrackerTheme.colors.primaryBackground,
                disabledIndicatorColor = MedTrackerTheme.colors.primary400,
                focusedIndicatorColor = MedTrackerTheme.colors.primary400,
                focusedContainerColor = MedTrackerTheme.colors.secondaryBackground,
                errorIndicatorColor = MedTrackerTheme.colors.sysError,
                focusedLabelColor = MedTrackerTheme.colors.primary400,
                unfocusedLabelColor = MedTrackerTheme.colors.secondaryLabel,
                errorLabelColor = MedTrackerTheme.colors.sysError,
                errorSupportingTextColor = MedTrackerTheme.colors.sysError,
                errorCursorColor = MedTrackerTheme.colors.sysError,
            ),
    )
}

@Composable
fun FlyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MedTrackerTheme.typography.bodyMedium,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors()
) {
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp/* top = 1.dp, bottom = 1.dp */)
            .border(
                width = 0.25.dp,
                color = MedTrackerTheme.colors.opaqueSeparator,
                shape = RectangleShape
            ),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        textStyle = textStyle,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MedTrackerTheme.colors.primaryBackground,
                        RoundedCornerShape(6.dp)
                    )
                    .height(32.dp)
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.CenterStart
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
    isPrimaryColor: Boolean = false,
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
        textStyle = MedTrackerTheme.typography.bodyMedium,
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
        label = {
            label?.let {
                Text(
                    it,
                    color = MedTrackerTheme.colors.primaryLabel
                )
            }
        },
        placeholder = {
            placeholder?.let {
                Text(
                    it,
                    color = MedTrackerTheme.colors.secondaryLabel
                )
            }
        },
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

@Composable
internal fun CustomTextField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    fontSize: TextUnit = MedTrackerTheme.typography.bodyMedium.fontSize
) {
    var text by rememberSaveable { mutableStateOf("") }
    BasicTextField(
        modifier = modifier
            .background(
                MedTrackerTheme.colors.secondaryBackground,
                MaterialTheme.shapes.small,
            )
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        cursorBrush = SolidColor(MedTrackerTheme.colors.primary400),
        textStyle = LocalTextStyle.current.copy(
            color = MedTrackerTheme.colors.primaryBackgroundGrouped,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()

                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeholderText,
                            style = LocalTextStyle.current.copy(
                                color = MedTrackerTheme.colors.primaryLabel,
                                fontSize = fontSize
                            )
                        )
                    }
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}

@Preview(
)
@Composable
fun FlyTextFieldPreview() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        /*   FlyTextField(
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
          ) */
        /*     FlyTextField(
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
            CustomTextField(
              leadingIcon = {
                Icon(
                  imageVector = Icons.Default.Email,
                  contentDescription = null
                )
              },
              trailingIcon = {
                Icon(
                  imageVector = Icons.Default.Lock,
                  contentDescription = null
                )
              },
              placeholderText = "Enter your email",
              modifier = Modifier
            )
            MyOutlinedTextField(
              value = "value",
              onValueChange = {},
              label = "label",
              placeholder = "placeholder",
            ) */
        /*     FlyTableTextField(
              value = "",
              onValueChange = {},
              label = "label",
              supportingText = "Supporting Text",
              placeholder = "Placeholder",
              enabled = true,
              isError = false,
              errorMessage = null,
              modifier = Modifier,
              visualTransformation = VisualTransformation.None,
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            ) */
    }

}

class FlyTableTextFieldPreviewParameterProvider :
    PreviewParameterProvider<Boolean> {

    override val values = sequenceOf(true, false)
    override val count: Int = values.count()
}

@Preview
@Composable
fun FlyTableTextFieldPreview(
    @PreviewParameter(FlyTableTextFieldPreviewParameterProvider::class)
    isPrimaryColor: Boolean,
) {
    FlyTableTextField(
        value = "value",
        onValueChange = {},
        isPrimaryColor = isPrimaryColor,
        label = "label",
    )
}