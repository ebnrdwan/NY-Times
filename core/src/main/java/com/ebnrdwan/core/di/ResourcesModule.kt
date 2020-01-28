package com.ebnrdwan.core.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object ResourcesModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @JvmStatic
    @Reusable
    @Provides
    fun provideResources(application: Application): Resources = application.resources


}