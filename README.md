# Tlih
An Experiment to build Retained Dagger Component on top of SimpleStack's GlobalServices.

Why
---

If you use [Zhuinden's SimpleStack](https://github.com/Zhuinden/simple-stack) you know that it has an awesome feature called ScopedServices.
SimpleStack is more than a **navigation manager** and you can also use it to create scoped services that will keep state through process death if you use its `Bundleable`.

GlobalServices are `ScopedServices` bound to an **global scopes**. They survive configuration change (device rotation/activity recreation) and you can use them
to provide common global services to others **scoped services*.

For example, you can provide `application` as a `global service` without needing a lot of casts and whatever else you need to do to find the app context.

How
---

SimpleStack works pretty well with Dagger, Koin, Kodein (probably) if you use one of them. In my case, I almost use Dagger as it solves all my problems.
But another problem came out: I need a dagger **component** which will outerlive Activity. What I can do to solve that? Well, I present to you SimpleStack's Scoping mechanism.

**Tlih** is an experiment built on top of **SimpleStack's GlobalServices**. Check you the test case to see more:

```Kotlin
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
```
