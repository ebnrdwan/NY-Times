package com.ebnrdwan.task.di

import com.ebnrdwan.task.presentation.ApplicationController

class AppTest : ApplicationController() {
    override val appComponent: TestAppComponent by lazy {
        applicationInjector()
    }

    override fun applicationInjector(): TestAppComponent {
        return DaggerTestAppComponent.factory().createWith(this)
    }
}