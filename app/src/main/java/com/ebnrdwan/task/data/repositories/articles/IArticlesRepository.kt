package com.ebnrdwan.task.data.repositories.articles

import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import io.reactivex.Single


interface IArticlesRepository {
    fun fetchArticles(): Single<ArticlesEntity>
}

