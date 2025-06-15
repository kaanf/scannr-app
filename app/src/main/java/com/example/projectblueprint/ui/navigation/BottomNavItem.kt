package com.example.projectblueprint.ui.navigation

import androidx.annotation.DrawableRes
import com.example.projectblueprint.R

sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val selectedIconRes: Int,
    @DrawableRes val unselectedIconRes: Int
) {
    data object Home : BottomNavItem(
        route = "home",
        title = "Home",
        selectedIconRes = R.drawable.ic_home_filled,
        unselectedIconRes = R.drawable.ic_home_outlined
    )
    
    data object Files : BottomNavItem(
        route = "files",
        title = "Files",
        selectedIconRes = R.drawable.ic_folder_filled,
        unselectedIconRes = R.drawable.ic_folder_outlined
    )
    
    data object Premium : BottomNavItem(
        route = "premium",
        title = "Premium",
        selectedIconRes = R.drawable.ic_star_filled,
        unselectedIconRes = R.drawable.ic_star_outlined
    )
    
    data object Account : BottomNavItem(
        route = "account",
        title = "Account",
        selectedIconRes = R.drawable.ic_profile_filled,
        unselectedIconRes = R.drawable.ic_profile_outlined
    )
}