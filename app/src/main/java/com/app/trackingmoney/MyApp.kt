package com.app.trackingmoney

import android.app.Application
import com.app.trackingmoney.data.di.repoModule
import com.app.trackingmoney.data.di.roomModule
import com.app.trackingmoney.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(
                roomModule,
                repoModule,
                viewModelModule
            ))
        }
    }
}