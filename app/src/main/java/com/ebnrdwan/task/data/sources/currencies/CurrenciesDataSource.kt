package com.ebnrdwan.task.data.sources.currencies

import com.ebnrdwan.task.BuildConfig
import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.task.data.service.CurrenciesApi
import io.reactivex.Single

open class CurrenciesDataSource constructor(private val service: CurrenciesApi) :
    ICurrenciesDataSource {
    override fun getCurrencies(): Single<CurrenciesResponse> {
        return service.getCurrencies(BuildConfig.API_KEY)
    }
}