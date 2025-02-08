package ru.kpfu.itis.fifthsemwork.navigation

import ru.kpfu.itis.common.navigation.AuthNavProvider
import ru.kpfu.itis.common.navigation.FavoritesNavProvider
import ru.kpfu.itis.common.navigation.HomeNavProvider
import ru.kpfu.itis.common.navigation.SearchNavProvider

data class Navigator(
    val search: SearchNavProvider,
    val home: HomeNavProvider,
    val favorites: FavoritesNavProvider,
    val auth: AuthNavProvider,
)