package com.ebnrdwan.task.domain

import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.task.data.repositories.currencies.ICurrenciesRepository
import com.ebnrdwan.task.data.repositories.mock.MockCurrenciesRepository
import com.ebnrdwan.task.data.repositories.mock.MockDataSource
import com.ebnrdwan.task.util.Constants.currenciesResponseFileName
import com.ebnrdwan.task.util.Constants.baseJsonPath
import com.ebnrdwan.task.util.loadJsonFromResources
import com.google.gson.Gson
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class CurrenciesUseCaseTest {
    private lateinit var repository: ICurrenciesRepository
    private lateinit var useCase: CurrenciesUseCase
    private lateinit var response: CurrenciesResponse

    @Before
    fun setUp() {
        repository = MockCurrenciesRepository()
        useCase = CurrenciesUseCase(repository)
    }

    @Test
    fun `when reading CurrenciesApiResponse file_ it shouldn't be null`() {
        val stream =
            CurrenciesUseCaseTest::class.java.getResourceAsStream(
                baseJsonPath.plus(currenciesResponseFileName)
            )
        assertNotNull(stream)
    }

    @Test
    fun `when mapping data from CurrenciesApiResponse file into POJO object_ shouldn't be null `() {
        val data = loadJsonFromResources(currenciesResponseFileName)
        val obj = Gson().fromJson(data, CurrenciesResponse::class.java)
        assertNotNull(obj)
    }

    @Test
    operator fun invoke() {
        response = MockDataSource.getFakeCurrenciesResponse()
        useCase.invoke()      //call
            .test()
            .assertValue(response)  //assert test
    }
}