package ru.kpfu.itis.fifthsemwork.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kpfu.itis.api.domain.repository.GeniusRepository
import ru.kpfu.itis.api.repository.FavoriteSongsRepository
import ru.kpfu.itis.common.navigation.AuthNavProvider
import ru.kpfu.itis.common.navigation.FavoritesNavProvider
import ru.kpfu.itis.common.navigation.HomeNavProvider
import ru.kpfu.itis.common.navigation.SearchNavProvider
import ru.kpfu.itis.impl.data.repository.FavoriteSongsRepositoryImpl
import ru.kpfu.itis.impl.data.repository.GeniusRepositoryImpl
import ru.kpfu.itis.impl.navigation.AuthNavProviderImpl
import ru.kpfu.itis.impl.navigation.FavoritesNavProviderImpl
import ru.kpfu.itis.impl.navigation.HomeNavProviderImpl
import ru.kpfu.itis.impl.navigation.SearchNavProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindFavoritesNavProviderToFavoritesNavProviderImpl(repositoryImpl: FavoritesNavProviderImpl) : FavoritesNavProvider

    @Binds
    @Singleton
    fun bindHomeNavProviderToHomeNavProviderImpl(repositoryImpl: HomeNavProviderImpl) : HomeNavProvider

    @Binds
    @Singleton
    fun bindSearchNavProviderToSearchNavProviderImpl(impl: SearchNavProviderImpl): SearchNavProvider

    @Binds
    @Singleton
    fun bindAuthNavProviderToAuthNavProviderImpl(impl: AuthNavProviderImpl): AuthNavProvider

}