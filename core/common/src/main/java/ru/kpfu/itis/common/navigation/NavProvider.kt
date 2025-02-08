package ru.kpfu.itis.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

sealed interface NavProvider {

    fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit,
    )

}