package com.example.projectblueprint.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectblueprint.ui.theme.Primary
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Shape
import androidx.compose.material3.MaterialTheme

@Composable
fun DefaultButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    backgroundColor: Color = Primary,
    contentColor: Color = Color.White,
    iconColor: Color = Color.Unspecified,
    cornerRadius: Int = 28,
    height: Int = 52,
    borderColor: Color? = null,
    borderWidth: Float = 1f
) {
    val shape: Shape = RoundedCornerShape(cornerRadius.dp)
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .let { m ->
                if (borderColor != null) m.border(borderWidth.dp, borderColor, shape) else m
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = shape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .padding(horizontal = 24.dp, vertical = 0.dp),
            contentAlignment = Alignment.Center
        ) {
            if (icon != null) {
                androidx.compose.foundation.layout.Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Image(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        colorFilter = if (iconColor != Color.Unspecified) ColorFilter.tint(iconColor) else null
                    )
                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleMedium.copy(color = contentColor)
                    )
                }
            } else {
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium.copy(color = contentColor)
                )
            }
        }
    }
}

@Composable
@Preview
fun DefaultButtonPreview() {
    DefaultButton(
        text = "Next",
        onClick = {}
    )
}
