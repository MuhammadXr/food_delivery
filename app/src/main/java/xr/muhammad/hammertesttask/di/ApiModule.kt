package xr.muhammad.hammertesttask.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xr.muhammad.hammertesttask.App
import xr.muhammad.hammertesttask.api.remote.FoodApi
import xr.muhammad.hammertesttask.api.remote.LocationApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideFoodApi(): FoodApi {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val BASE_URL = "https://customer.api.delever.uz/"

        val client = OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ChuckerInterceptor.Builder((App.instance)).build())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(FoodApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationApi(): LocationApi {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val BASE_URL = "https://geocode-maps.yandex.ru/"

        val client = OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ChuckerInterceptor.Builder((App.instance)).build())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LocationApi::class.java)
    }
}