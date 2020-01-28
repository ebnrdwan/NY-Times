package com.ebnrdwan.core.util

import android.util.Log
import com.ebnrdwan.core.BuildConfig


object Logger {
    fun log(tag: String = "Logger_Tag-->: ", message: String) {
        if (BuildConfig.DEBUG) { // log only in debug mode
            Log.d(tag, message)
        }
    }


}

