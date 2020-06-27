package dev.itscorey.rc

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isSameAs
import com.zhuinden.simplestackextensions.navigatorktx.backstack
import com.zhuinden.simplestackextensions.servicesktx.lookup
import dev.itscorey.rc.main.injection.GlobalService
import dev.itscorey.rc.main.MainActivity
import dev.itscorey.rc.main.injection.ActivityRetainedComponent
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityRetainedComponentTest {
    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun shouldActivityRetainedComponentSurviveConfigChanges() {
        var component1: ActivityRetainedComponent? = null
        var component2: ActivityRetainedComponent? = null

        rule.scenario.run {
            onActivity { activity ->
                component1 = activity.backstack.lookup<GlobalService>().component
            }

            recreate()

            onActivity { activity ->
                component2 = activity.backstack.lookup<GlobalService>().component
            }
        }

        assertThat(component2).isNotNull().isSameAs(component1)
    }
}