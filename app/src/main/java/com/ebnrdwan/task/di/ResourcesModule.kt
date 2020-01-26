package com.ebnrdwan.task.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.ebnrdwan.task.R
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

    @Reusable
    @Provides
    fun provideAppDrawable(application: Application): Drawable? {
        return ContextCompat.getDrawable(application, R.mipmap.ic_app_launcher)
    }
}