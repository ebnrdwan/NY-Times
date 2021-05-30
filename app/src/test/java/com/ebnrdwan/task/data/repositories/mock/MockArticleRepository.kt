package com.ebnrdwan.task.data.repositories.mock

import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.task.data.repositories.currencies.ICurrenciesRepository
import io.reactivex.Single


class MockArticleRepository : ICurrenciesRepository {
    override fun fetchCurrencies(): Single<CurrenciesResponse> {
        return Single.just(MockDataSource.getFakeArticlesResponse())
    }

}

