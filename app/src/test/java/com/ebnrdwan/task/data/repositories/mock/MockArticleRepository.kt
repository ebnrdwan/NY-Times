package com.ebnrdwan.task.data.repositories.mock

import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.ebnrdwan.task.data.repositories.articles.IArticlesRepository
import io.reactivex.Single


class MockArticleRepository : IArticlesRepository {
    override fun fetchArticles(): Single<ArticlesEntity> {
        return Single.just(MockDataSource.getFakeArticlesResponse())
    }

}

