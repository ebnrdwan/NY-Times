package com.ebnrdwan.task.di.currencies

import com.ebnrdwan.core.di.FragmentScope
import com.ebnrdwan.task.presentation.currencies.CurrencyListFragment
import com.ebnrdwan.task.presentation.details.CurrencyConversionFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CurrenciesModule::class])
interface CurrenciesSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CurrenciesSubcomponent
    }

    fun inject(currencyListFragment: CurrencyListFragment)
    fun inject(detailsFragment: CurrencyConversionFragment)
}