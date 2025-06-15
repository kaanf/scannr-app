package com.example.projectblueprint.ui.base

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectblueprint.R
import com.example.projectblueprint.ui.theme.*

@Composable
fun FormInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    onFocusChange: (Boolean) -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    
    val hasValue = value.isNotEmpty()
    val isActive = isFocused || hasValue
    
    val borderThickness by animateDpAsState(
        targetValue = if (isActive) 2.dp else 1.dp,
        animationSpec = tween(durationMillis = 300),
        label = "borderThickness"
    )
    
    val borderColor by animateColorAsState(
        targetValue = when {
            isError -> MaterialTheme.colorScheme.error
            isActive -> Primary
            else -> Primary.copy(alpha = 0.5f)
        },
        animationSpec = tween(durationMillis = 300),
        label = "borderColor"
    )
    
    val labelColor by animateColorAsState(
        targetValue = if (isActive) Grey900 else Grey500,
        animationSpec = tween(durationMillis = 300),
        label = "labelColor"
    )
    
    val textColor by animateColorAsState(
        targetValue = if (hasValue) Grey900 else Grey400,
        animationSpec = tween(durationMillis = 300),
        label = "textColor"
    )
    
    val iconAlpha by animateFloatAsState(
        targetValue = if (isActive) 1f else 0.5f,
        animationSpec = tween(durationMillis = 300),
        label = "iconAlpha"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled && !readOnly
            ) {
                focusRequester.requestFocus()
            }
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = labelColor
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    onFocusChange(focusState.isFocused)
                },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = textColor,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(Primary),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Grey400,
                                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                                )
                            )
                        }
                        innerTextField()
                    }
                    
                    if (trailingIcon != null) {
                        Box(
                            modifier = Modifier
                                .alpha(iconAlpha)
                                .padding(start = 8.dp)
                        ) {
                            trailingIcon()
                        }
                    }
                }
            }
        )
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(borderThickness)
                .background(borderColor)
        )
        
        if (isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedFormInputPreview() {
    var value by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        FormInput(
            label = "Form Title",
            value = value,
            onValueChange = { value = it },
            placeholder = "Placeholder"
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        FormInput(
            label = "Email",
            value = "john.doe@example.com",
            onValueChange = { },
            placeholder = "Enter your email"
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        FormInput(
            label = "Password",
            value = "password123",
            onValueChange = { },
            placeholder = "Enter your password",
            visualTransformation = PasswordVisualTransformation(),
            isError = true,
            errorMessage = "Password must be at least 8 characters"
        )
    }
}

@Preview(showBackground = true, name = "Interactive Preview")
@Composable
fun AnimatedFormInputInteractivePreview() {
    MaterialTheme {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            FormInput(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                placeholder = "Enter your email",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            FormInput(
                label = "Password",
                value = password,
                onValueChange = { password = it },
                placeholder = "Enter your password",
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (passwordVisible) R.drawable.ic_apple else R.drawable.ic_apple
                            ),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = Grey600
                        )
                    }
                }
            )
        }
    }
}