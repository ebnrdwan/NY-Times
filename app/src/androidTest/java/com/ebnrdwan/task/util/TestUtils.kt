/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ebnrdwan.task.util

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor
import com.ebnrdwan.corepresentation.base.BaseFragment


/**
 * Useful test methods common to all screens
 */
object TestUtils {

    /**
     * Gets an Activity in the RESUMED stage.
     *
     *
     * This method should never be called from the Main thread. In certain situations there might
     * be more than one Activities in RESUMED stage, but only one is returned.
     * See [ActivityLifecycleMonitor].
     */
    // The array is just to wrap the Activity and be able to access it from the Runnable.

    private fun rotateToLandscape(activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    private fun rotateToPortrait(activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun rotateOrientation(activity: Activity) {
        val currentOrientation = activity.resources.configuration.orientation

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            rotateToPortrait(activity)
        } else {
            rotateToLandscape(activity)
        }
    }

    /**
     * Returns the content description for the navigation button view in the toolbar.
     */
    fun getToolbarNavigationContentDescription(
        activity: Activity, @IdRes toolbar1: Int
    ): String? {
        val toolbar = activity.findViewById<View>(toolbar1) as Toolbar
        return if (toolbar != null) {
            toolbar.navigationContentDescription as String?
        } else {
            throw RuntimeException("No toolbar found.")
        }
    }

    inline fun <reified T : BaseFragment>
            launchFragment(
        navController: NavController?
    ) {
        val scenario = launchFragmentInContainer<T>()
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }
}



