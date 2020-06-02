package com.jlmcdeveloper.cofresenha

import android.app.Application
import com.jlmcdeveloper.cofresenha.di.activityModules
import com.jlmcdeveloper.cofresenha.di.appModules
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class AppApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        val listModules = activityModules + appModules

        startKoin {
            androidContext(this@AppApplication)
            modules(listModules)
        }
    }
}