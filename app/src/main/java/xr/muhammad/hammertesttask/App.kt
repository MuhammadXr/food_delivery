package xr.muhammad.hammertesttask

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        //  AppDatabase.init(this)
//        LocalDataSource.init(this)
//        AuthRepositoryImpl.init()
    }
}