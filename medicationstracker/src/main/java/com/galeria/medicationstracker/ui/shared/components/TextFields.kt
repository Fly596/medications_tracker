package com.galeria.medicationstracker.ui.shared.components

/*
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoIconTextFieldDecorator(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    isFocused: Boolean = false,
    isTyping: Boolean = false,
    label: String = "",
    placeholder: String = ""
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .background(
                color = when {
                    isFocused -> Color.LightGray
                    else -> Color.Transparent
                },
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                BorderStroke(1.dp, if (isFocused) Color(0xFF007AFF) else Color(0x4d3c3c43)),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        decorator = { innerTextField ->
            Column {
                if (text.isEmpty()) {
                    Text(
                        text = if (isFocused || isTyping) label else placeholder,
                        color = Color.Gray
                    )
                }
                innerTextField()
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IconTextFieldDecorator(
    text: String = "",
    label: String = "Label",
    title: String = "Title",
    placeholder: String = "placeholder",
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onValueChange: (String) -> Unit,
    isFocused: Boolean = false,
    isTyping: Boolean = false,
) {
    BasicTextField(
        value = text,
        textStyle = MaterialTheme.typography.bodyMedium,
        onValueChange = onValueChange, // Color(0xFF007AFF) else Color(0x4d3c3c43)
        cursorBrush = SolidColor(Color(0xFF007AFF))*/
/* if (isFocused || isTyping) SolidColor(Color(0xFF007AFF)) else SolidColor(
            Color(
                0x4d3c3c43
            )
        ) *//*
,
        modifier = modifier

            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height
                drawLine(
                    color = Color(0x4d3c3c43),
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
            .padding(vertical = 16.dp),
        decorator = { innerTextField ->
            Column(modifier = Modifier.fillMaxWidth()) {

                // Title.
                Text(text = title, style = MaterialTheme.typography.labelLarge)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
*/
/*                     if (text.isEmpty()) {
                        Text(
                            text = if (isFocused || isTyping) label else placeholder,
                            color = Color(0xff3c3c43)
                        )
                    } *//*

                    if (text.isEmpty()) {
                        Text(
                            text = label,
                            color = Color(0xff3c3c43)
                        )
                    }

                    innerTextField()
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color(0x993c3c43)
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HIGOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    textStyle: TextStyle = TextStyle.Default
) {
    BasicTextField2(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text
        ),
        textStyle = textStyle,
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerTextField ->
            Surface(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                innerTextField()
            }
            */
/*             Row(
                Modifier
                    .background(Color.LightGray, RoundedCornerShape(percent = 30))
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.MailOutline, contentDescription = "Mail Icon")
                Spacer(Modifier.width(16.dp))
                innerTextField()
            } *//*

        },
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            .border(
                width = 1.dp,
                color = Color.Gray, // Adjust color to match HIG
                shape = RoundedCornerShape(8.dp)
            )
    )
    Text(
        text = label,
        modifier = Modifier.padding(start = 12.dp, bottom = 4.dp),
        color = Color.Gray // Adjust color to match HIG
    )
}


@Preview(
    name = "TextFields", showSystemUi = true,
    device = "spec:parent=pixel_8,navigation=buttons"
)
@Composable
private fun PreviewTextFields() {
    SpeechRecognitionAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
*/
/*             HIGOutlinedTextField(
                value = "HIGOutlinedTextField",
                onValueChange = {},
                label = "Email",
                modifier = Modifier.fillMaxWidth()
            ) *//*

            IconTextFieldDecorator(
                // text = "IconTextFieldDecorator",
                text = "passwonaosn",
                title = "Password",
                placeholder = "Enter your password",
                onValueChange = {},
                icon = Icons.Default.Clear,
                isFocused = true,
                isTyping = true,
            )

            */
/*             NoIconTextFieldDecorator(
                            text = "NoIconTextFieldDecorator",
                            onValueChange = {},
                            label = "Email",
                            placeholder = "Enter your email"
                        ) *//*

            */
/*             IconTextFieldDecorator(
                            text = "IconTextFieldDecorator",
                            onValueChange = {},
                            icon = Icons.Default.MailOutline,
                             *//*
*/
/* isFocused = true, *//*
*/
/*
                isTyping = false,
                label = "Email",
                placeholder = "Placeholder"
            ) *//*

        }
    }
}*/
