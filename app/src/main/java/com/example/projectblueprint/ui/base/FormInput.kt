package com.example.projectblueprint.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectblueprint.ui.theme.*

@Composable
fun FormInput(
    title: String,
    value: String?,
    placeholder: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    rightIcon: Painter? = null,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = Grey900
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = enabled && onClick != null) { onClick?.invoke() }
                .padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value ?: placeholder,
                style = if (value.isNullOrEmpty()) {
                    MaterialTheme.typography.bodyMedium.copy(color = Grey400, fontWeight = FontWeight.Bold)
                } else {
                    MaterialTheme.typography.bodyMedium.copy(color = Grey900, fontWeight = FontWeight.Bold)
                },
                modifier = Modifier.weight(1f)
            )
            if (rightIcon != null) {
                Spacer(modifier = Modifier.width(8.dp))
                androidx.compose.foundation.Image(
                    painter = rightIcon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    alpha = if (value.isNullOrEmpty()) 0.5f else 1f
                )
            }
        }
        Divider(
            color = Primary,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
@Preview
fun FormInputPreview() {
    MaterialTheme {
        FormInput(
            title = "Title",
            value = "Value",
            placeholder = "Placeholder",
            onClick = {}
        )
    }
}