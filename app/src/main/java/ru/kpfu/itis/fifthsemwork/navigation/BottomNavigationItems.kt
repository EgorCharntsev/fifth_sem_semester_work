package ru.kpfu.itis.fifthsemwork.navigation

import ru.kpfu.itis.common.navigation.Route
import ru.kpfu.itis.designsystem.components.navigation.BottomNavigationItem
import ru.kpfu.itis.fifthsemwork.R

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        label = R.string.label_home,
        icon = R.drawable.home,
        route = getRoute(Route.BottomMenu.Home)
    ),
    BottomNavigationItem(
        label = R.string.label_search,
        icon = R.drawable.search,
        route = getRoute(Route.BottomMenu.Search)
    ),
    BottomNavigationItem(
        label = R.string.label_favorites,
        icon = R.drawable.note,
        route = getRoute(Route.BottomMenu.Favourites)
    ),
    BottomNavigationItem(
        label = R.string.label_profile,
        icon = R.drawable.person,
        route = getRoute(Route.BottomMenu.Profile)
    ),
)

private fun getRoute(route: Route): String = route::class.qualifiedName.toString()