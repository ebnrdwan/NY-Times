package com.ebnrdwan.task.domain

import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import io.reactivex.Single

interface ICurrenciesUseCase {

   operator fun invoke(): Single<CurrenciesResponse>
}