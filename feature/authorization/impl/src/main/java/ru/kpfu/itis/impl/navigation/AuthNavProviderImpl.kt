package ru.kpfu.itis.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.kpfu.itis.common.navigation.AuthNavProvider
import ru.kpfu.itis.common.navigation.Route
import ru.kpfu.itis.impl.presentation.ui.AuthScreen
import javax.inject.Inject

class AuthNavProviderImpl @Inject constructor() : AuthNavProvider {
    override fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit
    ) {
        composable(route = Route.BottomMenu.Home.toString()) {
            onBottomBarVisibilityChanged(true)
            AuthScreen(navController = controller)
        }
    }
}