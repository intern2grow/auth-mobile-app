package dev.awd.auth

import android.app.Application
import dev.awd.auth.di.AppModule
import dev.awd.auth.di.AppModuleImpl

class AuthApplication : Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl
    }

}