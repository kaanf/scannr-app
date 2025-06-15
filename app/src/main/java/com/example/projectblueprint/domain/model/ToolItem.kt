package com.example.projectblueprint.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class ToolItem(
    val id: String,
    val name: String,
    @DrawableRes val icon: Int,
    val backgroundColor: Color
)