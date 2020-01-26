package com.ebnrdwan.task.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    fun isInternetOn(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}

