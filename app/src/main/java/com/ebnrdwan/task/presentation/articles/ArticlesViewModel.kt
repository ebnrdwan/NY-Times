package com.ebnrdwan.task.presentation.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ebnrdwan.task.data.dto.articles.ArticleItem
import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.ebnrdwan.core.data.error.manager.ErrorManager
import com.ebnrdwan.core.data.models.UiState
import com.ebnrdwan.task.domain.IArticlesUseCase
import com.ebnrdwan.corepresentation.base.BaseViewModel
import com.ebnrdwan.task.util.Constants
import com.ebnrdwan.core.util.Logger
import com.ebnrdwan.corepresentation.utils.NoInternetException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ArticlesViewModel @Inject constructor(private val articlesUseCase: IArticlesUseCase) :
    BaseViewModel() {
    private val _uiStatModel = MutableLiveData<UiState>().apply { }
    private val _listOfArticles = MutableLiveData<List<ArticleItem>>()
    private val _selectedArticle = MutableLiveData<ArticleItem>()


    @Inject
    lateinit var errorManager: ErrorManager

    /*================get uiState as Immutable Live drawerList================*/
    fun getUiStateModel(): LiveData<UiState> = _uiStatModel

    fun getSelectedArticle(): MutableLiveData<ArticleItem> = _selectedArticle

    fun setSelectedArticle(article: ArticleItem) {
        _selectedArticle.value = article
    }


    fun getArticleList(): LiveData<List<ArticleItem>> = _listOfArticles

    fun loadArticles() = fetchArticles(UiState.LOADING)

    fun reloadArticles() = fetchArticles(UiState.RELOADING)


    /*================fetch Articles remotely================*/
    private fun fetchArticles(uiState: UiState) {
        addDisposable(
            articlesUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    //todo fixMe: sideEffects is non idiomatic in reactive paradigm
                    _uiStatModel.value = uiState
                }
                .subscribeArticle()
        )
    }


    private fun Single<ArticlesEntity>.subscribeArticle(): Disposable {
        return this.subscribeBy(
            onSuccess = {
                Logger.log(message = "${Constants.Logging.RX_TAG_NEXT}: $it")
                if (it.news.isEmpty()) _uiStatModel.value = UiState.NODATA
                else {
                    _listOfArticles.value = it.news
                    _uiStatModel.value = UiState.SUCCESS
                }

            },
            onError = {
                Logger.log(message = "${Constants.Logging.RX_TAG_ERROR}: $it")
                if (it is NoInternetException)
                    _uiStatModel.value = UiState.NOINTERNET
                else _uiStatModel.value = UiState.ERROR.apply {
                    message = errorManager.getError(it).message
                }
            }
        )
    }

}