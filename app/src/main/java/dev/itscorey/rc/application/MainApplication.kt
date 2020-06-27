package dev.itscorey.rc.application

import android.app.Application

class MainApplication : Application() {
    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.factory().create(this)
    }
}