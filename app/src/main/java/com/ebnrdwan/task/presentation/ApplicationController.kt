package com.ebnrdwan.task.presentation

import android.app.Application
import com.ebnrdwan.task.di.AppComponent
import com.ebnrdwan.task.di.DaggerAppComponent


open class ApplicationController : Application() {

 open   val appComponent: AppComponent by lazy {
        applicationInjector()
    }

    open fun applicationInjector(): AppComponent {
        return DaggerAppComponent.factory().createWith(this)
    }

}