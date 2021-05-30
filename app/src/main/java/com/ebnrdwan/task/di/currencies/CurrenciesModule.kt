package com.ebnrdwan.task.di.currencies

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ebnrdwan.core.di.FragmentScope
import com.ebnrdwan.core.di.CurrenciesSource
import com.ebnrdwan.core.di.ViewModelKey
import com.ebnrdwan.task.data.repositories.currencies.CurrenciesRepository
import com.ebnrdwan.task.data.repositories.currencies.ICurrenciesRepository
import com.ebnrdwan.task.data.service.CurrenciesApi
import com.ebnrdwan.task.data.sources.currencies.CurrenciesDataSource
import com.ebnrdwan.task.data.sources.currencies.ICurrenciesDataSource
import com.ebnrdwan.task.domain.CurrenciesUseCase
import com.ebnrdwan.task.domain.ICurrenciesUseCase
import com.ebnrdwan.task.presentation.currencies.CurrenciesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [BindViewModel::class])
object CurrenciesModule {

    @FragmentScope
    @JvmStatic
    @Provides
    fun bindApiCurrencyServices(retrofit: Retrofit): CurrenciesApi =
        retrofit.create(CurrenciesApi::class.java)


    @FragmentScope
    @JvmStatic
    @CurrenciesSource
    @Provides
    fun bindCurrencyDataSource(apiService: CurrenciesApi): ICurrenciesDataSource =
        CurrenciesDataSource(apiService)


    @FragmentScope
    @JvmStatic
    @Provides
    fun provideCurrenciesRepository(
        application: Application,
        @CurrenciesSource currenciesDataSource: ICurrenciesDataSource
    ): ICurrenciesRepository =
        CurrenciesRepository(currenciesDataSource, application)

    @FragmentScope
    @JvmStatic
    @Provides
    fun provideCurrenciesUseCase(
        currenciesDataSource: ICurrenciesRepository
    ): ICurrenciesUseCase =
        CurrenciesUseCase(currenciesDataSource)

}

@Module
abstract class BindViewModel {
    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(CurrenciesViewModel::class)
    abstract fun bindViewModel(viewModel: CurrenciesViewModel): ViewModel

}