package com.ebnrdwan.task.data.sources.currencies


import com.ebnrdwan.core.data.sources.IDataSource
import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import io.reactivex.Single

interface ICurrenciesDataSource : IDataSource {
    fun getCurrencies(): Single<CurrenciesResponse>
}