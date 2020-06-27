package dev.itscorey.rc.featurea

import android.os.Parcelable
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestack.navigator.DefaultViewKey
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import com.zhuinden.simplestack.navigator.changehandlers.FadeViewChangeHandler
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider
import com.zhuinden.simplestackextensions.servicesktx.add
import com.zhuinden.simplestackextensions.servicesktx.lookup
import dev.itscorey.rc.R
import dev.itscorey.rc.main.injection.GlobalService
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainFeatureViewKey(
    val name: String = "Feature"
) : DefaultViewKey, DefaultServiceProvider.HasServices, Parcelable {
    override fun layout(): Int {
        return R.layout.main_feature_screen
    }

    override fun viewChangeHandler(): ViewChangeHandler {
        return FadeViewChangeHandler()
    }

    override fun getScopeTag(): String {
        return "MainFeature"
    }

    override fun bindServices(serviceBinder: ServiceBinder) {
        val component = serviceBinder.lookup<GlobalService>().component
        serviceBinder.add(component.vm())
    }
}