package xr.muhammad.hammertesttask.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xr.muhammad.hammertesttask.utils.connectivity_status.ConnectivityObserver
import xr.muhammad.hammertesttask.utils.connectivity_status.ConnectivityObserverImpl

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {
    @Provides
    fun connection(@ApplicationContext context: Context): ConnectivityObserver {
        return ConnectivityObserverImpl(context)
    }
}