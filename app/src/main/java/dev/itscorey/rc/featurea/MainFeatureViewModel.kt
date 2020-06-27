package dev.itscorey.rc.featurea

import com.zhuinden.simplestack.Bundleable
import com.zhuinden.statebundle.StateBundle
import dev.itscorey.rc.main.injection.Retained
import javax.inject.Inject

@Retained
class MainFeatureViewModel @Inject constructor() : Bundleable {
    var value: Int = 1

    fun inc() = value++

    override fun toBundle(): StateBundle {
        return StateBundle().apply {
            putInt("value", value)
        }
    }

    override fun fromBundle(bundle: StateBundle?) {
        value = bundle?.getInt("value") ?: value
    }
}