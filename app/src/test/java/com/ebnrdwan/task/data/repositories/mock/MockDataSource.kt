package com.ebnrdwan.task.data.repositories.mock

import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.task.data.dto.currencies.Currency
import com.ebnrdwan.task.util.Constants.currenciesResponseFileName
import com.ebnrdwan.task.util.Constants.currencyItemResponseFileName
import com.ebnrdwan.task.util.loadJsonFromResources
import com.google.gson.Gson

object MockDataSource {

    fun getFakeCurrenciesResponse(): CurrenciesResponse {
        val json = loadJsonFromResources(currenciesResponseFileName)
        val response = Gson().fromJson(json, CurrenciesResponse::class.java)
        response.convertRatesToCurrencyList(response.rates)
        return response
    }


    fun getFakeCurrencyItem(): Currency {
        val json = loadJsonFromResources(currencyItemResponseFileName)
        return Gson().fromJson(json, Currency::class.java)
    }


}