package com.ebnrdwan.task.domain

import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.ebnrdwan.task.data.repositories.articles.IArticlesRepository
import com.ebnrdwan.task.data.repositories.mock.MockArticleRepository
import com.ebnrdwan.task.data.repositories.mock.MockDataSource
import com.ebnrdwan.task.util.Constants.articlesResponseFileName
import com.ebnrdwan.task.util.Constants.baseJsonPath
import com.ebnrdwan.task.util.loadJsonFromResources
import com.google.gson.Gson
import org.junit.Assert.assertNotNull
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
    fun `when reading ArticlesApiResponse file_ it shouldn't be null`() {
        val stream =
            ArticlesUseCaseTest::class.java.getResourceAsStream(
                baseJsonPath.plus(
                    articlesResponseFileName
                )
            )
        assertNotNull(stream)
    }

    @Test
    fun `when mapping data from ArticlesApiResponse file into POJO object_ shouldn't be null `() {
        val data = loadJsonFromResources(articlesResponseFileName)
        val obj = Gson().fromJson(data, ArticlesEntity::class.java)
        assertNotNull(obj)
    }

    @Test
    operator fun invoke() {
        response = MockDataSource.getFakeArticlesResponse()
        useCase.invoke()      //call
            .test()
            .assertValue(response)  //assert test
    }
}