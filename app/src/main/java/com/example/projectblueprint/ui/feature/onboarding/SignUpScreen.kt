package com.example.projectblueprint.ui.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectblueprint.R
import com.example.projectblueprint.ui.base.FormInput
import com.example.projectblueprint.ui.base.DefaultButton
import com.example.projectblueprint.ui.base.AnimatedCheckboxWithLabel
import com.example.projectblueprint.ui.theme.*

@Composable
fun SignUpScreen(
    onSignUp: (String, String, String, Boolean) -> Unit = { _, _, _, _ -> },
    onGoogleClick: () -> Unit = {},
    onAppleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreeToTerms by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_apple),
                contentDescription = "Back",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onBack() },
                tint = Grey900
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.displaySmall,
            color = Grey900
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sign up to get started with your account",
            style = MaterialTheme.typography.bodySmall,
            color = Grey600
        )
        Spacer(modifier = Modifier.height(28.dp))
        
        FormInput(
            label = "Full Name",
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = "Enter your full name",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        FormInput(
            label = "Email",
            value = email,
            onValueChange = { email = it },
            placeholder = "Enter your email",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        FormInput(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            placeholder = "Create a password",
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
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
        
        Spacer(modifier = Modifier.height(16.dp))
        
        AnimatedCheckboxWithLabel(
            checked = agreeToTerms,
            onCheckedChange = { agreeToTerms = it },
            label = {
                Text(
                    text = buildAnnotatedString {
                        append("I agree to the ")
                        withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.SemiBold)) {
                            append("Terms & Conditions")
                        }
                        append(" and ")
                        withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.SemiBold)) {
                            append("Privacy Policy")
                        }
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = Grey900
                )
            }
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        DefaultButton(
            text = "Create Account",
            onClick = { onSignUp(fullName, email, password, agreeToTerms) },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Primary,
            contentColor = Color.White,
            height = 56
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(modifier = Modifier.weight(1f), color = Grey200)
            Text(
                text = "  or sign up with  ",
                color = Grey500,
                style = MaterialTheme.typography.bodySmall
            )
            Divider(modifier = Modifier.weight(1f), color = Grey200)
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SocialSignUpButton(icon = R.drawable.ic_google, onClick = onGoogleClick)
            SocialSignUpButton(icon = R.drawable.ic_apple, onClick = onAppleClick)
            SocialSignUpButton(icon = R.drawable.ic_facebook, onClick = onFacebookClick)
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account? ",
                style = MaterialTheme.typography.bodySmall,
                color = Grey600
            )
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Primary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.clickable { onSignInClick() }
            )
        }
    }
}

@Composable
fun SocialSignUpButton(icon: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, Grey200, CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpScreen()
    }
}