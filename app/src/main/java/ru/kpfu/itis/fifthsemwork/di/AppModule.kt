package ru.kpfu.itis.fifthsemwork.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kpfu.itis.common.navigation.AuthNavProvider
import ru.kpfu.itis.common.navigation.FavoritesNavProvider
import ru.kpfu.itis.common.navigation.HomeNavProvider
import ru.kpfu.itis.common.navigation.SearchNavProvider
import ru.kpfu.itis.fifthsemwork.App
import ru.kpfu.itis.fifthsemwork.navigation.Navigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideBaseApplication(@ApplicationContext context: Context): App {
        return context as App
    }

    @Provides
    fun provideNavigator(
        auth: AuthNavProvider,
        search: SearchNavProvider,
        home: HomeNavProvider,
        favorites: FavoritesNavProvider,
    ): Navigator {
        return Navigator(search, home, favorites, auth, )
    }
}