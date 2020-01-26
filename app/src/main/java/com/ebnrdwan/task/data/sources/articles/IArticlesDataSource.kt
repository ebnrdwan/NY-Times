package com.ebnrdwan.task.data.sources.articles


import com.ebnrdwan.task.data.sources.IDataSource
import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import io.reactivex.Single

interface IArticlesDataSource : IDataSource{
    fun getArticles(): Single<ArticlesEntity>
}