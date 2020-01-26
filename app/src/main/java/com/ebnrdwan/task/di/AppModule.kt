package com.ebnrdwan.task.di

import dagger.Module

@Module(
    includes = [
        ResourcesModule::class,
        NetworkModule::class,
        ErrorModule::class,
        ViewModelFactoryBuilder::class]
)
object AppModule