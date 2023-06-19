package xr.muhammad.hammertesttask.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xr.muhammad.hammertesttask.room.FoodDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        FoodDatabase::class.java,
        "food_data_base"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: FoodDatabase) = database.foodDao()

    @Singleton
    @Provides
    fun provideCartDao(database: FoodDatabase) = database.cartDao()

}