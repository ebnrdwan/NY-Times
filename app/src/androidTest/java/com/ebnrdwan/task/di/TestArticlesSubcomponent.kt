package com.ebnrdwan.task.di

import com.ebnrdwan.core.di.FragmentScope
import com.ebnrdwan.task.di.currencies.CurrenciesModule
import com.ebnrdwan.task.ui.articles.CurrencyListFragmentTest
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CurrenciesModule::class])
interface TestArticlesSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TestArticlesSubcomponent
    }

    fun inject(articlesFragment: CurrencyListFragmentTest)
}