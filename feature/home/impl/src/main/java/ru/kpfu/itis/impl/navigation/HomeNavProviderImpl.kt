package ru.kpfu.itis.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.kpfu.itis.common.navigation.HomeNavProvider
import ru.kpfu.itis.common.navigation.Route
import ru.kpfu.itis.impl.presentation.ui.MainScreen
import javax.inject.Inject

class HomeNavProviderImpl @Inject constructor() : HomeNavProvider {
    override fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit
    ) {
        composable(route = Route.BottomMenu.Home.toString()) {
            onBottomBarVisibilityChanged(true)
            MainScreen(navController = controller)
        }
    }
}