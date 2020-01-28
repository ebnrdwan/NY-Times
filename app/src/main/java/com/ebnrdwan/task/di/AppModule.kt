package com.ebnrdwan.task.di

import com.ebnrdwan.core.di.ErrorModule
import com.ebnrdwan.core.di.NetworkModule
import com.ebnrdwan.core.di.ResourcesModule
import com.ebnrdwan.core.di.ViewModelFactoryBuilder
import dagger.Module

@Module(
    includes = [
        ResourcesModule::class,
        NetworkModule::class,
        ErrorModule::class,
        ViewModelFactoryBuilder::class]
)
object AppModule