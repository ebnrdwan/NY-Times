package com.ebnrdwan.task.presentation.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ebnrdwan.core.data.error.manager.ErrorManager
import com.ebnrdwan.core.data.models.UiState
import com.ebnrdwan.core.util.Logger
import com.ebnrdwan.core.util.SingleLiveEvent
import com.ebnrdwan.corepresentation.base.BaseViewModel
import com.ebnrdwan.corepresentation.utils.NoInternetException
import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.task.data.dto.currencies.Currency
import com.ebnrdwan.task.domain.ICurrenciesUseCase
import com.ebnrdwan.task.util.Constants
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CurrenciesViewModel @Inject constructor(private val articlesUseCase: ICurrenciesUseCase) :
    BaseViewModel() {
    private val _uiStatModel = MutableLiveData<UiState>().apply { }
    private val _currencyList = MutableLiveData<List<Currency>>()
    private val _selectedCurrency = MutableLiveData<Currency>()
    private var currentBaseValue: Double = 1.0
    private var currentSelectedCurrency: Currency? = null
    private val currentConvertedAmount = SingleLiveEvent<Double>()

    @Inject
    lateinit var errorManager: ErrorManager

    fun getUiStateModel(): LiveData<UiState> = _uiStatModel

    fun getSelectedCurrency(): MutableLiveData<Currency> = _selectedCurrency

    fun setSelectedCurrency(currency: Currency) {
        currentSelectedCurrency = currency
        _selectedCurrency.value = currency
    }


    fun getCurrenciesList(): LiveData<List<Currency>> = _currencyList

    fun loadCurrencies() = fetchCurrencies(UiState.LOADING)

    fun reloadCurrencies() = fetchCurrencies(UiState.RELOADING)


    /*================fetch Currencies remotely================*/
    private fun fetchCurrencies(uiState: UiState) {
        addDisposable(
            articlesUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _uiStatModel.value = uiState
                }
                .subscribeCurrency()
        )
    }


    private fun Single<CurrenciesResponse>.subscribeCurrency(): Disposable {
        return this.subscribeBy(
            onSuccess = { response ->
                onResponseSuccess(response)
            },
            onError = {
                onResponseError(it)
            }
        )
    }

    private fun onResponseError(it: Throwable) {
        Logger.log(message = "${Constants.Logging.RX_TAG_ERROR}: $it")
        if (it is NoInternetException)
            _uiStatModel.value = UiState.NOINTERNET
        else _uiStatModel.value = UiState.ERROR.apply {
            message = errorManager.getError(it).message
        }
    }

    private fun onResponseSuccess(response: CurrenciesResponse) {
        if (response.rates == null) _uiStatModel.value = UiState.NODATA
        else {
            emitCurrencyListData(response)
        }
    }

    private fun emitCurrencyListData(it: CurrenciesResponse) {
        it.convertRatesToCurrencyList(it.rates)
        _currencyList.value = it.currenciesList
        _uiStatModel.value = UiState.SUCCESS
    }

    fun setNewBaseAmount(newValue: Double?) {
        currentBaseValue = newValue ?: 1.0
        emitNewConversionData()

    }

    fun getCurrentBaseValueLiveData(): Double? {
        return if (currentBaseValue > 1) currentBaseValue else null
    }

    fun getCurrentConvertedValue(): SingleLiveEvent<Double> {
        return currentConvertedAmount
    }

    private fun emitNewConversionData() {
        val converted = currentBaseValue * (currentSelectedCurrency?.rate ?: 1.0)
        currentConvertedAmount.postValue(converted)
    }

}