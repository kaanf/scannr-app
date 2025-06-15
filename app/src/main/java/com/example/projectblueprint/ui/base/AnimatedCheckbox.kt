package com.example.projectblueprint.ui.base

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectblueprint.ui.theme.*

@Composable
fun AnimatedCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val checkboxColor = Color(0xFF3B82F6)

    val backgroundColor by animateColorAsState(
        targetValue = if (checked) checkboxColor else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "backgroundColor"
    )
    
    val borderColor by animateColorAsState(
        targetValue = if (enabled) checkboxColor else checkboxColor.copy(alpha = 0.38f),
        animationSpec = tween(durationMillis = 300),
        label = "borderColor"
    )
    
    val checkmarkScale by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = spring(
            dampingRatio = 0.5f,
            stiffness = 500f
        ),
        label = "checkmarkScale"
    )
    
    val checkboxScale by animateFloatAsState(
        targetValue = if (checked) 0.95f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "checkboxScale"
    )
    
    val semanticsDescription = if (checked) "Checked" else "Unchecked"
    
    Box(
        modifier = modifier
            .size(24.dp)
            .semantics {
                role = Role.Checkbox
                stateDescription = semanticsDescription
            }
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(
                    bounded = false,
                    radius = 24.dp,
                    color = checkboxColor
                ),
                enabled = enabled,
                onClick = { onCheckedChange(!checked) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .scale(checkboxScale)
                .clip(RoundedCornerShape(6.dp))
                .background(backgroundColor)
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(6.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .scale(checkmarkScale),
                tint = Color.White
            )
        }
    }
}

@Composable
fun AnimatedCheckboxWithLabel(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Primary),
                enabled = enabled,
                onClick = { onCheckedChange(!checked) }
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.weight(1f)) {
            label()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedCheckboxPreview() {
    MaterialTheme {
        var checked1 by remember { mutableStateOf(false) }
        var checked2 by remember { mutableStateOf(true) }
        var checked3 by remember { mutableStateOf(false) }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Standalone checkbox
            AnimatedCheckbox(
                checked = checked1,
                onCheckedChange = { checked1 = it }
            )
            
            // Checkbox with label
            AnimatedCheckboxWithLabel(
                checked = checked2,
                onCheckedChange = { checked2 = it },
                label = {
                    Text(
                        text = "Remember me",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Grey900
                    )
                }
            )
            
            // Disabled checkbox
            AnimatedCheckboxWithLabel(
                checked = checked3,
                onCheckedChange = { checked3 = it },
                enabled = false,
                label = {
                    Text(
                        text = "Disabled option",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Grey500
                    )
                }
            )
            
            // Complex label example
            AnimatedCheckboxWithLabel(
                checked = checked3,
                onCheckedChange = { checked3 = it },
                label = {
                    Column {
                        Text(
                            text = "I agree to the Terms & Conditions",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Grey900
                        )
                        Text(
                            text = "You must agree to continue",
                            style = MaterialTheme.typography.bodySmall,
                            color = Grey600
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "Interactive States")
@Composable
fun AnimatedCheckboxStatesPreview() {
    MaterialTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Unchecked
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AnimatedCheckbox(
                    checked = false,
                    onCheckedChange = { }
                )
                Text(
                    text = "Unchecked",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            // Checked
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AnimatedCheckbox(
                    checked = true,
                    onCheckedChange = { }
                )
                Text(
                    text = "Checked",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            // Disabled Unchecked
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AnimatedCheckbox(
                    checked = false,
                    onCheckedChange = { },
                    enabled = false
                )
                Text(
                    text = "Disabled",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            // Disabled Checked
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AnimatedCheckbox(
                    checked = true,
                    onCheckedChange = { },
                    enabled = false
                )
                Text(
                    text = "Disabled Checked",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}