package com.ebnrdwan.task.presentation.currencies

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ebnrdwan.task.R
import com.ebnrdwan.core.data.models.UiState
import com.ebnrdwan.task.presentation.ApplicationController
import com.ebnrdwan.corepresentation.base.BaseFragment
import com.ebnrdwan.corepresentation.base.exportErrorView
import com.ebnrdwan.corepresentation.base.exportTvError
import com.ebnrdwan.corepresentation.base.export_loading
import com.ebnrdwan.corepresentation.utils.fade
import com.ebnrdwan.corepresentation.utils.hide
import com.ebnrdwan.corepresentation.utils.show
import com.ebnrdwan.task.data.dto.currencies.Currency
import kotlinx.android.synthetic.main.fragment_articles.*
import java.util.Collections.emptyList
import javax.inject.Inject


class CurrencyListFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_articles

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val articlesViewModel by activityViewModels<CurrenciesViewModel> { viewModelFactory }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            (it.application as ApplicationController)
                .appComponent.registerCurrenciesComponent()
                .create()
                .inject(this)
        }
    }

    private var _mainAdapter: CurrenciesAdapterPaged? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        articlesViewModel.loadCurrencies()
    }


    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        articlesViewModel.reloadCurrencies()
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
        articlesViewModel.getCurrenciesList().observe(viewLifecycleOwner, Observer {
            _mainAdapter?.updateArticles(it)
        })
        observeOnUiState()
    }


    private fun initializeAdapter() {
        _mainAdapter = activity?.let { activity ->
            CurrenciesAdapterPaged(
                activity,
                emptyList()
            ) { item ->
                onArticleItemClick(item)
            }
        }
        rvArticles.layoutManager = LinearLayoutManager(context)
        rvArticles.adapter = _mainAdapter
    }

    private fun onArticleItemClick(article: Currency) {
        articlesViewModel.setSelectedCurrency(article)
//        findNavController().navigate(
//            ArticlesFragmentDirections.actionNavHomeToNavDetails(
//                article.id.toInt()
//            )
//        )
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
        export_loading.show()
        rvArticles.hide()
        exportErrorView.hide()

    }

    private fun showReloading() {
        isRefreshing(true)
        export_loading.hide()
        rvArticles.hide()
        exportErrorView.hide()

    }

    private fun showData() {
        isRefreshing(false)
        export_loading.hide()
        rvArticles.fade()
        exportErrorView.hide()


    }

    private fun showError(message: Int) {
        isRefreshing(false)
        exportTvError.text = getString(message)
        export_loading.hide()
        rvArticles.hide()
        exportErrorView.fade()
    }


}