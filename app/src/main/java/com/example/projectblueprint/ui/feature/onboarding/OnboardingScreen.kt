package com.example.projectblueprint.ui.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectblueprint.ui.base.DefaultButton

@Composable
fun OnboardingScreen(
    onSkipClicked: () -> Unit,
    onNextClicked: () -> Unit,
) {

}


// Preview için Composable fonksiyon
@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    MaterialTheme { // Projenizdeki temanızı kullanın
        OnboardingScreen(
            onSkipClicked = {},
            onNextClicked = {},
        )
    }
}