package com.ebnrdwan.task.domain

import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.task.data.repositories.currencies.ICurrenciesRepository
import io.reactivex.Single
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(private val repository: ICurrenciesRepository) : ICurrenciesUseCase {
    /* invoke is the only exposed function in use-case
        to insure that use-cases has only one and single responsibility*/
    override operator fun invoke(): Single<CurrenciesResponse> = repository.fetchCurrencies()
}