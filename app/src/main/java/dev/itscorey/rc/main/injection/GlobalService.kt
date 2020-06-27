package dev.itscorey.rc.main.injection

import android.app.Application
import com.zhuinden.simplestack.ScopedServices
import dev.itscorey.rc.application.MainApplication
import javax.inject.Inject

class GlobalService @Inject constructor(
    private val app: Application
) : ScopedServices.Registered {
    lateinit var component: ActivityRetainedComponent
        private set

    override fun onServiceUnregistered() = Unit

    override fun onServiceRegistered() {
        component = (app as MainApplication).component
            .viewModelStoreComponentFactory().create()
    }
}