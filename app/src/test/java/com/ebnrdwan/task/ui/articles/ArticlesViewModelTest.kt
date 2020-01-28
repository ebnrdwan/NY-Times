package com.ebnrdwan.task.ui.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.ebnrdwan.core.data.error.Error
import com.ebnrdwan.core.data.error.manager.ErrorManager
import com.ebnrdwan.core.data.error.mapper.ErrorMapper
import com.ebnrdwan.core.data.models.UiState
import com.ebnrdwan.task.data.repositories.articles.IArticlesRepository
import com.ebnrdwan.task.data.repositories.mock.MockArticleRepository
import com.ebnrdwan.task.data.repositories.mock.MockDataSource
import com.ebnrdwan.task.domain.ArticlesUseCase
import com.ebnrdwan.task.presentation.articles.ArticlesViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ArticlesViewModelTest {
    private lateinit var repository: IArticlesRepository
    private lateinit var useCase: ArticlesUseCase
    private lateinit var viewmodel: ArticlesViewModel
    private lateinit var response: ArticlesEntity
    private lateinit var errorManager: ErrorManager
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        repository = MockArticleRepository()
        useCase = ArticlesUseCase(repository)
        errorManager =
        ErrorManager(ErrorMapper())
        response = MockDataSource.getFakeArticlesResponse()
        viewmodel = ArticlesViewModel(useCase)
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
        assertNull(viewmodel.getSelectedArticle().value)
    }

    @Test
    fun setSelectedArticle() {
        val item = MockDataSource.getFakeArticleItem()
        viewmodel.getSelectedArticle().observeForever {
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