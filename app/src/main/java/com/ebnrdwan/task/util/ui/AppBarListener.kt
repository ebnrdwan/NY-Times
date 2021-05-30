package com.ebnrdwan.task.util.ui

import com.google.android.material.appbar.AppBarLayout

abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {
    enum class State {
        EXPANDED, COLLAPSED, IDLE
    }

    private var mCurrentState =
        State.IDLE

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        offsetAlphaOnScrolling(1 - (appBarLayout.y / appBarLayout.totalScrollRange * -1))
        mCurrentState = if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED, i)
            }
            State.EXPANDED
        } else if (Math.abs(i) >= appBarLayout.totalScrollRange) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED, i)
            }
            State.COLLAPSED
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE, i)
            }
            State.IDLE
        }
    }

    abstract fun onStateChanged(
        appBarLayout: AppBarLayout?,
        state: State?,
        i: Int
    )

    abstract fun offsetAlphaOnScrolling(offsetAlpha: Float)
}