package com.ebnrdwan.task.util.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.appbar.AppBarLayout
import setVisibilityState
import kotlin.math.abs

fun AppBarLayout.hideToolbarItemsOnExpand(
    toolbarTitle: TextView,
    toolbarIcon: ImageView,
     appbarTitle: TextView,
     appbarIcon: ImageView
) {

    this.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
        override fun onStateChanged(
            appBarLayout: AppBarLayout?,
            state: State?,
            i: Int
        ) {
        }

        override fun offsetAlphaOnScrolling(offsetAlpha: Float) {
            animateHideOnExpand(offsetAlpha, toolbarTitle)
            animateHideOnExpand(offsetAlpha, toolbarIcon)
            animateShowOnExpand(offsetAlpha, appbarTitle)
            animateShowOnExpand(offsetAlpha, appbarIcon)
        }
    })
}


 fun animateHideOnExpand(
    offsetAlpha: Float,
    view: View) {

    val calculatedAlpha = abs(1 - offsetAlpha)
    view?.alpha = calculatedAlpha
    view?.setVisibilityState(calculatedAlpha > 0)

}


 fun animateShowOnExpand(
    offsetAlpha: Float,
    view: View
) {

    view?.alpha = offsetAlpha
    view?.setVisibilityState(offsetAlpha > 0)
}


