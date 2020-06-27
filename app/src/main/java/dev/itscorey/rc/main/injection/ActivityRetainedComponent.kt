package dev.itscorey.rc.main.injection

import dagger.Subcomponent
import dev.itscorey.rc.featurea.MainFeatureViewModel
import javax.inject.Scope

@Scope @Retention annotation class Retained // ~> must have a better name

@Retained
@Subcomponent
interface ActivityRetainedComponent {
    fun vm(): MainFeatureViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityRetainedComponent
    }
}