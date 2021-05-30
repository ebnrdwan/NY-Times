package com.ebnrdwan.task.data.repositories.currencies

import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import io.reactivex.Single


interface ICurrenciesRepository {
    fun fetchCurrencies(): Single<CurrenciesResponse>
}

