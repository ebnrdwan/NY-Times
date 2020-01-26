package com.ebnrdwan.task.domain

import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import io.reactivex.Single

interface IArticlesUseCase {

   operator fun invoke(): Single<ArticlesEntity>
}