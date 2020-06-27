package dev.itscorey.rc.featurea

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.Toast
import com.zhuinden.simplestackextensions.navigatorktx.backstack
import com.zhuinden.simplestackextensions.servicesktx.lookup

class MainFeatureView(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    val viewModel: MainFeatureViewModel = backstack.lookup()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Toast.makeText(context, viewModel.value.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onDetachedFromWindow() {
        viewModel.inc()
        super.onDetachedFromWindow()
    }
}