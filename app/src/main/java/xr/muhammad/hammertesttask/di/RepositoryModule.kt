package xr.muhammad.hammertesttask.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xr.muhammad.hammertesttask.repository.AppRepository
import xr.muhammad.hammertesttask.repository.impl.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bind(impl: AppRepositoryImpl): AppRepository

}