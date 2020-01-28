package com.ebnrdwan.task.di

import com.ebnrdwan.core.di.FragmentScope
import com.ebnrdwan.task.presentation.articles.ArticlesFragment
import com.ebnrdwan.task.presentation.details.ArticleDetailsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ArticlesModule::class])
interface ArticlesSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArticlesSubcomponent
    }

    fun inject(articlesFragment: ArticlesFragment)
    fun inject(DetailsFragment: ArticleDetailsFragment)
}