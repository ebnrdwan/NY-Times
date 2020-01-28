package com.ebnrdwan.core.data.repositories

import android.content.Context
import com.ebnrdwan.core.util.NetworkUtils.isNetworkConnected
import io.reactivex.Single

abstract class BaseRepository(val context: Context) {
     fun checkInternetConnection(): Single<Boolean> {
        return Single.just(isNetworkConnected(context))
    }
}