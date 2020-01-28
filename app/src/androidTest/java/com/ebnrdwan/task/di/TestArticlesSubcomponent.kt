package com.ebnrdwan.task.di

import com.ebnrdwan.core.di.FragmentScope
import com.ebnrdwan.task.ui.articles.ArticlesFragmentTest
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ArticlesModule::class])
interface TestArticlesSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TestArticlesSubcomponent
    }

    fun inject(articlesFragment: ArticlesFragmentTest)
}