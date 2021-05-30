package com.ebnrdwan.task.util.ui

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.AppBarLayout


interface IAppbarChangeOffsetWithSwipeToRefresh {


    fun setAppBarChangeListener(
        appBarLayout: AppBarLayout,
        swipeToRefresh: SwipeRefreshLayout
    ) {
        appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?, i: Int) {

                if (!swipeToRefresh.isRefreshing)
                    swipeToRefresh.isEnabled = i == 0
            }

            override fun offsetAlphaOnScrolling(offsetAlpha: Float) {

            }

        })
    }

}