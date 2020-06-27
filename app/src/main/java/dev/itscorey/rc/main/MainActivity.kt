package dev.itscorey.rc.main

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.zhuinden.simplestack.GlobalServices
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider
import com.zhuinden.simplestackextensions.servicesktx.add
import dev.itscorey.rc.application.MainApplication
import dev.itscorey.rc.featurea.MainFeatureViewKey
import dev.itscorey.rc.main.injection.GlobalService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var globalService: GlobalService // ~> must have a better name.

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).component.inject(this)

        super.onCreate(savedInstanceState)

        val rootContainer = Layout(this)
        setContentView(rootContainer)

        Navigator.configure()
            .setScopedServices(DefaultServiceProvider())
            .setGlobalServices(GlobalServices.builder().add(globalService).build()) // ~> may use Factory to avoid it of being override?
            .install(this, rootContainer, History.single(MainFeatureViewKey()))
    }

    private inner class Layout(context: Context) : FrameLayout(context) {
        init {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, MATCH_PARENT)
        }
    }
}