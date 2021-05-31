package com.ebnrdwan.task.di

import com.ebnrdwan.core.di.FragmentScope
import com.ebnrdwan.task.di.currencies.CurrenciesModule
import com.ebnrdwan.task.ui.currencies.CurrencyListFragmentTest
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CurrenciesModule::class])
interface TestCurrenciesSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TestCurrenciesSubcomponent
    }

    fun inject(currencyListFragmentTest: CurrencyListFragmentTest)
}