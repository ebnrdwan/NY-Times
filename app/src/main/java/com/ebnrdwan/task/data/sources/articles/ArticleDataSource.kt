package com.ebnrdwan.task.data.sources.articles

import com.ebnrdwan.task.BuildConfig
import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.ebnrdwan.task.data.service.ArticlesApi
import io.reactivex.Single

open class ArticleDataSource constructor(private val service: ArticlesApi) :
    IArticlesDataSource {
    override fun getArticles(): Single<ArticlesEntity> {
        return service.getArticlesNews(BuildConfig.API_KEY)
    }
}