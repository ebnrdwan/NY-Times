package com.ebnrdwan.task.ui.articles

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ebnrdwan.task.R
import com.ebnrdwan.task.data.dto.articles.ArticleItem
import com.ebnrdwan.task.data.models.UiState
import com.ebnrdwan.task.ui.ApplicationController
import com.ebnrdwan.task.ui.base.BaseFragment
import com.ebnrdwan.task.util.fade
import com.ebnrdwan.task.util.hide
import com.ebnrdwan.task.util.show
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.android.synthetic.main.handle__nodata_nointernet_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import java.util.Collections.emptyList
import javax.inject.Inject


class ArticlesFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_articles

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val articlesViewModel by activityViewModels<ArticlesViewModel> { viewModelFactory }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            (it.application as ApplicationController)
                .appComponent.registerArticleComponent()
                .create()
                .inject(this)
        }
    }

    private var _mainAdapter: ArticlesAdapterPaged? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        articlesViewModel.loadArticles()
    }


    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        articlesViewModel.reloadArticles()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.articles, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListeners()
        subscribeToViewModelObservables()
        initializeAdapter()

    }

    private fun setListeners() {
        swipe_refresh.setOnRefreshListener(refreshListener)
    }


    private fun subscribeToViewModelObservables() {
        articlesViewModel.getArticleList().observe(viewLifecycleOwner, Observer {
            _mainAdapter?.updateArticles(it)
        })
        observeOnUiState()
    }


    private fun initializeAdapter() {
        _mainAdapter = activity?.let { activity ->
            ArticlesAdapterPaged(
                activity,
                emptyList()
            ) { item ->
                onArticleItemClick(item)
            }
        }
        rvArticles.layoutManager = LinearLayoutManager(context)
        rvArticles.adapter = _mainAdapter
    }

    private fun onArticleItemClick(article: ArticleItem) {
        articlesViewModel.setSelectedArticle(article)
        findNavController().navigate(
            ArticlesFragmentDirections.actionNavHomeToNavDetails(
                article.id.toInt()
            )
        )
    }


    override fun observeOnUiState() {
        articlesViewModel.getUiStateModel().observe(viewLifecycleOwner, Observer {

            when (it) {
                is UiState.LOADING -> {
                    showLoading()
                }
                is UiState.RELOADING -> {
                    showReloading()
                }
                is UiState.SUCCESS -> {
                    showData()
                }
                is UiState.ERROR -> {
                    showError(it.message)
                }
                is UiState.NODATA -> {
                    showError(it.message)
                }
                is UiState.NOINTERNET -> {
                    showError(it.message)
                }
            }
        })
    }

    private fun isRefreshing(refreshing: Boolean) {
        swipe_refresh.isRefreshing = refreshing
    }

    private fun showLoading() {
        isRefreshing(false)
        loading_view.show()
        rvArticles.hide()
        error_view.hide()

    }

    private fun showReloading() {
        isRefreshing(true)
        loading_view.hide()
        rvArticles.hide()
        error_view.hide()

    }

    private fun showData() {
        isRefreshing(false)
        loading_view.hide()
        rvArticles.fade()
        error_view.hide()


    }

    private fun showError(message: Int) {
        isRefreshing(false)
        tv_error.text = getString(message)
        loading_view.hide()
        rvArticles.hide()
        error_view.fade()
    }


}