package ru.kpfu.itis.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.kpfu.itis.common.navigation.FavoritesNavProvider
import ru.kpfu.itis.common.navigation.Route
import ru.kpfu.itis.impl.presentation.ui.FavoritesScreen
import javax.inject.Inject

class FavoritesNavProviderImpl @Inject constructor() : FavoritesNavProvider {
    override fun NavGraphBuilder.registerGraph(
        controller: NavHostController,
        onBottomBarVisibilityChanged: (Boolean) -> Unit
    ) {
        composable(route = Route.BottomMenu.Home.toString()) {
            onBottomBarVisibilityChanged(true)
            FavoritesScreen(navController = controller)
        }
    }
}