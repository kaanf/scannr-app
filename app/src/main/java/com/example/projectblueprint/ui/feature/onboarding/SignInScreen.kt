package com.example.projectblueprint.ui.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectblueprint.R
import com.example.projectblueprint.ui.base.DefaultButton
import com.example.projectblueprint.ui.base.FormInput
import com.example.projectblueprint.ui.theme.*

@Composable
fun SignInScreen(
    onSignIn: (String, String, Boolean) -> Unit = { _, _, _ -> },
    onForgotPassword: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onAppleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(true) }

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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hello there 44B",
                style = MaterialTheme.typography.displaySmall,
                color = Grey900
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Please enter your email & password to sign in.",
            style = MaterialTheme.typography.bodySmall,
            color = Grey600
        )
        Spacer(modifier = Modifier.height(28.dp))
        FormInput(
            title = "Email",
            value = if (email.isNotEmpty()) email else null,
            placeholder = "Placeholder",
            onClick = {},
            rightIcon = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormInput(
            title = "Password",
            value = if (password.isNotEmpty()) "•".repeat(password.length) else null,
            placeholder = "Placeholder",
            onClick = {},
            rightIcon = painterResource(id = if (passwordVisible) R.drawable.ic_eye else R.drawable.ic_eye_off)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Primary,
                    uncheckedColor = Grey300
                )
            )
            Text(
                text = "Remember me",
                style = MaterialTheme.typography.bodySmall,
                color = Grey900
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Grey100, thickness = 1.dp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Forgot Password",
            style = MaterialTheme.typography.bodySmall.copy(color = Primary),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onForgotPassword() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Grey100, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(modifier = Modifier.weight(1f), color = Grey200)
            Text(
                text = "  or continue with  ",
                color = Grey500,
                style = MaterialTheme.typography.bodySmall
            )
            Divider(modifier = Modifier.weight(1f), color = Grey200)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SocialIconButton(icon = R.drawable.ic_google, onClick = onGoogleClick)
            SocialIconButton(icon = R.drawable.ic_apple, onClick = onAppleClick)
            SocialIconButton(icon = R.drawable.ic_facebook, onClick = onFacebookClick)
        }
        Spacer(modifier = Modifier.weight(1f))
        DefaultButton(
            text = "Sign In",
            onClick = { onSignIn(email, password, rememberMe) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        )
    }
}

@Composable
fun SocialIconButton(icon: Int, onClick: () -> Unit) {
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
fun SignInScreenPreview() {
    MaterialTheme {
        SignInScreen()
    }
}