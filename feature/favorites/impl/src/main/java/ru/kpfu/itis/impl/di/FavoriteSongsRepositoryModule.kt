package ru.kpfu.itis.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kpfu.itis.api.repository.FavoriteSongsRepository
import ru.kpfu.itis.impl.data.repository.FavoriteSongsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface FavoriteSongsRepositoryModule {

    @Binds
    fun bindFavoriteSongsRepositoryToFavoriteSongsRepositoryImpl(repositoryImpl: FavoriteSongsRepositoryImpl) : FavoriteSongsRepository
}