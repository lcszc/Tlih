package dev.itscorey.rc.application

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dev.itscorey.rc.main.MainActivity
import dev.itscorey.rc.main.injection.ActivityRetainedComponent

@Component
interface AppComponent {
    fun viewModelStoreComponentFactory(): ActivityRetainedComponent.Factory

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}