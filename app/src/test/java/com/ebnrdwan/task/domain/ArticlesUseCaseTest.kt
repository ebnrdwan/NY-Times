package com.ebnrdwan.task.domain

import com.ebnrdwan.task.data.repositories.mock.MockArticleRepository
import com.ebnrdwan.task.data.repositories.mock.MockDataSource
import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.ebnrdwan.task.data.repositories.articles.IArticlesRepository
import org.junit.Before
import org.junit.Test

class ArticlesUseCaseTest {
    private lateinit var repository: IArticlesRepository
    private lateinit var useCase: ArticlesUseCase
    private lateinit var response: ArticlesEntity

    @Before
    fun setUp() {
        repository = MockArticleRepository()
        useCase = ArticlesUseCase(repository)
    }

    @Test
    operator fun invoke() {
        response = MockDataSource.getFakeArticlesResponse()
        useCase.invoke()      //call
            .test()
            .assertValue(response)  //assert test

    }

}