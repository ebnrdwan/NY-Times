package com.ebnrdwan.task.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ebnrdwan.core.di.FragmentScope
import com.ebnrdwan.core.di.MainArticlesSource
import com.ebnrdwan.core.di.ViewModelKey
import com.ebnrdwan.task.data.repositories.articles.ArticlesRepository
import com.ebnrdwan.task.data.repositories.articles.IArticlesRepository
import com.ebnrdwan.task.data.service.ArticlesApi
import com.ebnrdwan.task.data.sources.articles.ArticleDataSource
import com.ebnrdwan.task.data.sources.articles.IArticlesDataSource
import com.ebnrdwan.task.domain.ArticlesUseCase
import com.ebnrdwan.task.domain.IArticlesUseCase
import com.ebnrdwan.task.presentation.articles.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [BindViewModel::class])
object ArticlesModule {

    @FragmentScope
    @JvmStatic
    @Provides
    fun bindApiArticleServices(retrofit: Retrofit): ArticlesApi =
        retrofit.create(ArticlesApi::class.java)


    @FragmentScope
    @JvmStatic
    @MainArticlesSource
    @Provides
    fun bindArticleDataSource(apiService: ArticlesApi): IArticlesDataSource =
        ArticleDataSource(apiService)


    @FragmentScope
    @JvmStatic
    @Provides
    fun provideArticlesRepository(
        application: Application,
        @MainArticlesSource articlesDataSource: IArticlesDataSource
    ): IArticlesRepository =
        ArticlesRepository(articlesDataSource, application)

    @FragmentScope
    @JvmStatic
    @Provides
    fun provideArticlesUseCase(
        articlesDataSource: IArticlesRepository
    ): IArticlesUseCase =
        ArticlesUseCase(articlesDataSource)

}

@Module
abstract class BindViewModel {
    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindViewModel(viewModel: ArticlesViewModel): ViewModel

}