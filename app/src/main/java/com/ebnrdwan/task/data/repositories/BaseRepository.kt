package com.ebnrdwan.task.data.repositories

import android.content.Context
import com.ebnrdwan.task.util.NetworkUtils.isInternetOn
import io.reactivex.Single

abstract class BaseRepository(val context: Context) {
     fun checkInternetConnection(): Single<Boolean> {
        return Single.just(isInternetOn(context))
    }
}