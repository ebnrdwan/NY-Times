package com.ebnrdwan.task.data.service


import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {
    companion object {
        private const val LATEST = "latest"
        private const val ACCESS_KEY = "access_key"
        private const val FORMAT = "format"
    }

    /*============== end-points-start================*/

    @GET(LATEST)
    fun getCurrencies(
        @Query(ACCESS_KEY) apiKey: String,
        @Query(FORMAT) format: Int=1
    ): Single<CurrenciesResponse>

    /*============== end points-end================*/
}
