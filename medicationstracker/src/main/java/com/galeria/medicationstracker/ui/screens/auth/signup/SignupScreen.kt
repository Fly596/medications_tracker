package com.galeria.medicationstracker.ui.screens.auth.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.componentsOld.*
import com.galeria.medicationstracker.ui.screens.auth.login.*
import com.galeria.medicationstracker.ui.theme.*

@Composable
fun SignupScreen(
    passedEmail: String = "",
    navigateHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignupScreenViewModel = viewModel(),
) {
    LaunchedEffect(Unit) { viewModel.updateEmail(passedEmail) }
    val state = viewModel.signupScreenState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            stringResource(R.string.sign_up_screen_title),
            style = MedTrackerTheme.typography.largeTitleEmphasized,
        )

        Spacer(modifier = Modifier.weight(1f))

        MyTextField(
            value = state.value.name,
            onValueChange = { viewModel.updateUserName(it) },
            isPrimaryColor = true,
            label = "Name",
            placeholder = "Name",
            modifier = Modifier.fillMaxWidth(),
        )
        MyTextField(
            value = state.value.age.toString(),
            onValueChange = {
                if (it.isNotEmpty()) {
                    viewModel.updateUserAge(it.toInt())
                } else {
                    viewModel.updateUserAge(0)
                }
            },
            isPrimaryColor = true,
            label = "Age",
            placeholder = "Age",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        MyTextField(
            value = state.value.email,
            onValueChange = { viewModel.updateEmail(it) },
            isPrimaryColor = true,
            isError = state.value.emailErrorMessage?.isNotEmpty() ?: false,
            errorMessage = state.value.emailErrorMessage,
            label = "Email",
            placeholder = "",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )

        MyTextField(
            value = state.value.password,
            onValueChange = { viewModel.updatePassword(it) },
            isPrimaryColor = true,
            isError = state.value.passwordErrorMessage?.isNotEmpty() ?: false,
            errorMessage = state.value.passwordErrorMessage,
            label = "Password",
            placeholder = "6 or more characters",
            supportingText = "6 or more characters",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation =
                if (state.value.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
        // Show password switch.
        RememberMeSwitch(
            checked = state.value.showPassword,
            onCheckedChange = { viewModel.isShowPasswordChecked(state.value.showPassword) },
        )
        // Список типов пользователя.
        var selectedType by remember { mutableStateOf(state.value.userType) }
        val options = UserType.entries.toTypedArray()

        FlySimpleCard(
            content = {
                Text(
                    "Account Type",
                    style = MedTrackerTheme.typography.title2,
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    options.forEach { type ->
                        Column(verticalArrangement = Arrangement.Center) {
                            MyRadioButton(
                                selected = selectedType == type,
                                onClick = {
                                    viewModel.updateUserType(selectedType)
                                    selectedType = type
                                },
                                caption = type.toString().lowercase()
                            )
                            // Text(text = form.toString().lowercase())
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        val context = LocalContext.current

        Row(verticalAlignment = Alignment.CenterVertically) {
            FlyTextButton(onClick = navigateHome) { Text(text = "Cancel") }

            Spacer(modifier = Modifier.weight(1f))

            FlyButton(onClick = {
                viewModel.onRegisterClick(
                    context,
                    onSignupSuccess = navigateHome
                )
            }) {
                Text(text = "Create Account")
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        // просто, чтобы положение полей и кнопок было таким же, как на экране входа.
        FlyTextButton(onClick = {}, enabled = false) { Text(text = "") }
    }
}

/**
 * Composable function that displays a radio button group for selecting user types.
 *
 * This function iterates through available user types and renders a radio button for each type.
 * The selected type is tracked and updated based on user interaction.
 *
 * @param viewModel The SignupScreenViewModel instance providing data and state.value for the signup screen.
 */
@Composable
fun UserTypesSelection(viewModel: SignupScreenViewModel) {
    var selectedType by remember { mutableStateOf(viewModel.signupScreenState.value.userType) }
    val options = UserType.entries.toTypedArray()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        options.forEach { type ->
            Row {
                MyRadioButton(
                    selected = selectedType == type,
                    onClick = {
                        selectedType = type
                    },
                    caption = type.toString().lowercase()
                )
            }
        }
    }

}
