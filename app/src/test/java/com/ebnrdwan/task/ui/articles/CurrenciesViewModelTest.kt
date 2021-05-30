package com.ebnrdwan.task.ui.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.core.data.error.Error
import com.ebnrdwan.core.data.error.manager.ErrorManager
import com.ebnrdwan.core.data.error.mapper.ErrorMapper
import com.ebnrdwan.core.data.models.UiState
import com.ebnrdwan.task.data.repositories.currencies.ICurrenciesRepository
import com.ebnrdwan.task.data.repositories.mock.MockArticleRepository
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

        repository = MockArticleRepository()
        useCase = CurrenciesUseCase(repository)
        errorManager =
        ErrorManager(ErrorMapper())
        response = MockDataSource.getFakeArticlesResponse()
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
    fun getSelectedArticle() {
// test initial value of selected article is null
        assertNull(viewmodel.getSelectedCurrency().value)
    }

    @Test
    fun setSelectedArticle() {
        val item = MockDataSource.getFakeArticleItem()
        viewmodel.getSelectedCurrency().observeForever {
            assertEquals(
                it, item
            )
        }
        viewmodel.setSelectedArticle(item)


    }


    @Test
    fun loadArticles() {
        viewmodel.loadArticles()
        viewmodel.getUiStateModel().value
        assert(viewmodel.getUiStateModel().value is UiState.LOADING)
    }

    @Test
    fun reloadArticles() {
        viewmodel.reloadArticles()
        viewmodel.getUiStateModel().value
        assert(viewmodel.getUiStateModel().value is UiState.RELOADING)
    }
}