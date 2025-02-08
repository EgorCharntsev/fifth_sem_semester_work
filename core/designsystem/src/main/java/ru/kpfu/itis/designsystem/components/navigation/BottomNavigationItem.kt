package ru.kpfu.itis.designsystem.components.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavigationItem (
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val route: String
)