package com.example.projectblueprint.ui.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectblueprint.R
import com.example.projectblueprint.ui.base.DefaultButton
import com.example.projectblueprint.ui.theme.Grey200
import com.example.projectblueprint.ui.theme.Grey900
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.border

@Composable
fun LoginScreen(
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    onAppleClick: () -> Unit = {},
    onSignInWithPassword: () -> Unit = {},
    onSignUp: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_illustration),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Let's you in",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        DefaultButton(
            text = "Continue with Google",
            icon = painterResource(id = R.drawable.ic_google),
            onClick = onGoogleClick,
            backgroundColor = Color.White,
            contentColor = Grey900,
            iconColor = Color.Unspecified,
            height = 52,
            borderColor = Grey200,
            borderWidth = 1f
        )
        Spacer(modifier = Modifier.height(12.dp))
        DefaultButton(
            text = "Continue with Facebook",
            icon = painterResource(id = R.drawable.ic_facebook),
            onClick = onFacebookClick,
            backgroundColor = Color.White,
            contentColor = Grey900,
            iconColor = Color.Unspecified,
            height = 52,
            borderColor = Grey200,
            borderWidth = 1f
        )
        Spacer(modifier = Modifier.height(12.dp))
        DefaultButton(
            text = "Continue with Apple",
            icon = painterResource(id = R.drawable.ic_apple),
            onClick = onAppleClick,
            backgroundColor = Color.White,
            contentColor = Grey900,
            iconColor = Color.Unspecified,
            height = 52,
            borderColor = Grey200,
            borderWidth = 1f
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
            Text(
                text = "  or  ",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Divider(modifier = Modifier.weight(1f), color = Color(0xFFE0E0E0))
        }
        Spacer(modifier = Modifier.height(24.dp))
        DefaultButton(
            text = "Sign in with password",
            onClick = onSignInWithPassword,
            backgroundColor = com.example.projectblueprint.ui.theme.Primary,
            contentColor = Color.White,
            cornerRadius = 28,
            height = 52
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account? ",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Text(
                text = "Sign up",
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 2.dp)
                    .background(Color.Transparent)
                    .clickable { onSignUp() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
} 