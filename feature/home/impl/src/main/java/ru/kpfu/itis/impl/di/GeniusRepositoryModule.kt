package ru.kpfu.itis.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kpfu.itis.api.domain.repository.GeniusRepository
import ru.kpfu.itis.impl.data.repository.GeniusRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface GeniusRepositoryModule {
    @Binds
    fun bindGeniusRepositoryToGeniusRepositoryImpl(repositoryImpl: GeniusRepositoryImpl) : GeniusRepository
}