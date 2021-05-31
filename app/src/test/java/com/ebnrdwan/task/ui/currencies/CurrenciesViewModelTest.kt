package com.ebnrdwan.task.ui.currencies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ebnrdwan.core.data.error.Error
import com.ebnrdwan.core.data.error.manager.ErrorManager
import com.ebnrdwan.core.data.error.mapper.ErrorMapper
import com.ebnrdwan.core.data.models.UiState
import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.task.data.repositories.currencies.ICurrenciesRepository
import com.ebnrdwan.task.data.repositories.mock.MockCurrenciesRepository
import com.ebnrdwan.task.data.repositories.mock.MockDataSource
import com.ebnrdwan.task.domain.CurrenciesUseCase
import com.ebnrdwan.task.presentation.currencies.CurrenciesViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CurrenciesViewModelTest {
    private lateinit var repository: ICurrenciesRepository
    private lateinit var useCase: CurrenciesUseCase
    private lateinit var viewmodel: CurrenciesViewModel
    private lateinit var response: CurrenciesResponse
    private lateinit var errorManager: ErrorManager

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        repository = MockCurrenciesRepository()
        useCase = CurrenciesUseCase(repository)
        errorManager =
            ErrorManager(ErrorMapper())
        response = MockDataSource.getFakeCurrenciesResponse()
        viewmodel = CurrenciesViewModel(useCase)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

    }


    @Test
    fun getErrorManager() {
        viewmodel.errorManager = errorManager
        assertEquals(viewmodel.errorManager.getError(TypeCastException()).message, Error().message)
    }

    @Test
    fun getUiStateModel() {
        // verify that initial state is null
        assertNull(viewmodel.getUiStateModel().value)
    }

    @Test
    fun getSelectedCurrency() {
// test initial value of selected currency is null
        assertNull(viewmodel.getSelectedCurrency().value)
    }

    @Test
    fun setSelectedCurrency() {
        val item = MockDataSource.getFakeCurrencyItem()
        viewmodel.getSelectedCurrency().observeForever {
            assertEquals(
                it, item
            )
        }
        viewmodel.setSelectedCurrency(item)


    }


    @Test
    fun loadCurrencies() {
        viewmodel.loadCurrencies()
        viewmodel.getUiStateModel().value
        assert(viewmodel.getUiStateModel().value is UiState.LOADING)
    }

    @Test
    fun reloadCurrencies() {
        viewmodel.reloadCurrencies()
        viewmodel.getUiStateModel().value
        assert(viewmodel.getUiStateModel().value is UiState.RELOADING)
    }
}