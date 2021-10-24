package com.mehyo.darbuka.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class DarbukaApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@DarbukaApp)
            // use modules
            modules(listOf(
                databaseModule,
                networkModule,
                repositoryModule,
                //useCaseModule,
                viewModelModule))
        }

    }
}