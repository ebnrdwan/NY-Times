package com.ebnrdwan.core.di

import com.ebnrdwan.core.data.error.mapper.ErrorMapper
import com.ebnrdwan.core.data.error.mapper.ErrorMapperInterface
import com.ebnrdwan.core.data.error.manager.ErrorFactory
import com.ebnrdwan.core.data.error.manager.ErrorManager
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorFactory

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperInterface
}
