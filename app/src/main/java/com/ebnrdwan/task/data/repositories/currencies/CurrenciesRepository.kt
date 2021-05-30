package com.ebnrdwan.task.data.repositories.currencies

import android.content.Context
import com.ebnrdwan.task.R
import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.core.data.repositories.BaseRepository
import com.ebnrdwan.task.data.sources.currencies.ICurrenciesDataSource
import com.ebnrdwan.core.di.CurrenciesSource
import com.ebnrdwan.corepresentation.utils.NoInternetException
import io.reactivex.Single
import javax.inject.Inject


class CurrenciesRepository @Inject constructor(
    @CurrenciesSource private val currenciesDataSource: ICurrenciesDataSource,
    private val mContext: Context
) : BaseRepository(mContext), ICurrenciesRepository {
    override fun fetchCurrencies():
            Single<CurrenciesResponse> {

        return checkInternetConnection()
            .flatMap { isConnected ->
                if (isConnected) currenciesDataSource.getCurrencies()
                else Single.error(
                    NoInternetException(
                        mContext.resources.getString(
                            R.string.no_internet
                        )
                    )
                )
            }
    }
}

