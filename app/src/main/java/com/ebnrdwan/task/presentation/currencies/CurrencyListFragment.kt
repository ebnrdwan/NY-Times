package com.ebnrdwan.task.presentation.currencies

import android.content.Context
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ebnrdwan.core.data.models.UiState
import com.ebnrdwan.corepresentation.base.BaseFragment
import com.ebnrdwan.corepresentation.base.exportErrorView
import com.ebnrdwan.corepresentation.base.exportTvError
import com.ebnrdwan.corepresentation.base.export_loading
import com.ebnrdwan.corepresentation.utils.fade
import com.ebnrdwan.corepresentation.utils.hide
import com.ebnrdwan.corepresentation.utils.show
import com.ebnrdwan.task.R
import com.ebnrdwan.task.data.dto.currencies.Currency
import com.ebnrdwan.task.presentation.ApplicationController
import com.ebnrdwan.task.util.ui.IAppbarChangeOffsetWithSwipeToRefresh
import com.ebnrdwan.task.util.ui.hideToolbarItemsOnExpand
import kotlinx.android.synthetic.main.appbar_layout.*
import kotlinx.android.synthetic.main.fragment_currencies.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.Collections.emptyList
import javax.inject.Inject


class CurrencyListFragment : BaseFragment(), IAppbarChangeOffsetWithSwipeToRefresh {
    override fun getLayout(): Int = R.layout.fragment_currencies

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val currenciesViewModel by activityViewModels<CurrenciesViewModel> { viewModelFactory }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            (it.application as ApplicationController)
                .appComponent.registerCurrenciesComponent()
                .create()
                .inject(this)
        }
    }

    private var currenciesAdapterPaged: CurrenciesAdapterPaged? = null


    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        currenciesViewModel.reloadCurrencies()
    }


    override fun bindOnViewModel() {
        currenciesViewModel.loadCurrencies()
        observeOnCurrencyList()
        observeOnUiState()
    }

    override fun setListeners() {
        swipe_refresh?.setOnRefreshListener(refreshListener)
        appbar_layout_view?.let { setAppBarChangeListener(it, swipe_refresh) }
        appbar_layout_view.hideToolbarItemsOnExpand(tv_toolbar_title, toolbar_icon,tvBaseCurrency,imgBaseCurrency)
    }

    override fun initViews() {
        initializeAdapter()
    }


    private fun observeOnCurrencyList() {
        currenciesViewModel.getCurrenciesList().observe(viewLifecycleOwner, Observer {
            currenciesAdapterPaged?.updateArticles(it)
        })
    }


    private fun initializeAdapter() {
        currenciesAdapterPaged = activity?.let { activity ->
            CurrenciesAdapterPaged(
                activity,
                emptyList()
            ) { item ->
                onArticleItemClick(item)
            }
        }
        rvCurrencies.layoutManager = LinearLayoutManager(context)
        rvCurrencies.adapter = currenciesAdapterPaged
    }

    private fun onArticleItemClick(article: Currency) {
        currenciesViewModel.setSelectedCurrency(article)
        findNavController().navigate(
            CurrencyListFragmentDirections.actionNavHomeToNavDetails()
        )
    }


    override fun observeOnUiState() {
        currenciesViewModel.getUiStateModel().observe(viewLifecycleOwner, Observer {

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
        swipe_refresh?.isRefreshing = refreshing
    }

    private fun showLoading() {
        isRefreshing(false)
        export_loading.show()
        rvCurrencies.hide()
        exportErrorView.hide()

    }

    private fun showReloading() {
        isRefreshing(true)
        export_loading.hide()
        rvCurrencies.hide()
        exportErrorView.hide()

    }

    private fun showData() {
        isRefreshing(false)
        export_loading.hide()
        rvCurrencies.fade()
        exportErrorView.hide()


    }

    private fun showError(message: Int) {
        isRefreshing(false)
        exportTvError.text = getString(message)
        export_loading.hide()
        rvCurrencies.hide()
        exportErrorView.fade()
    }


}