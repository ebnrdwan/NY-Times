package com.ebnrdwan.task.domain

import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.ebnrdwan.task.data.repositories.articles.IArticlesRepository
import io.reactivex.Single
import javax.inject.Inject

class ArticlesUseCase @Inject constructor(private val repository: IArticlesRepository) : IArticlesUseCase {
    /* invoke is the only exposed function in use-case
        to insure that use-cases has only one and single responsibility*/
    override operator fun invoke(): Single<ArticlesEntity> = repository.fetchArticles()
}