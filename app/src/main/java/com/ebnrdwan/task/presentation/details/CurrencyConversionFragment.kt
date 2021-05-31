package com.ebnrdwan.task.presentation.details

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ebnrdwan.corepresentation.base.BaseFragment
import com.ebnrdwan.task.R
import com.ebnrdwan.task.data.dto.currencies.Currency
import com.ebnrdwan.task.presentation.ApplicationController
import com.ebnrdwan.task.presentation.currencies.CurrenciesViewModel
import com.ebnrdwan.task.util.Constants.Ui.DEFAULT_DECIMALS
import kotlinx.android.synthetic.main.fragment_conversion.*
import onTextChangeDo
import setNumberTemplateWithSign
import javax.inject.Inject


class CurrencyConversionFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_conversion


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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        setListeners()
        bindOnViewModel()
    }

    override fun bindOnViewModel() {
        getCurrentCurrency()
        getCurrentConvertedValue()
        getCurrentBaseValue()
    }

    override fun initViews() {
        initBaseCurrencyEditText()
    }

    private fun initBaseCurrencyEditText() {
        edBaseCurrencyAmount.requestFocus()
        if (edBaseCurrencyAmount.hasFocus()) {
            val imm: InputMethodManager? =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(edBaseCurrencyAmount, InputMethodManager.SHOW_FORCED)
        }
    }

    override fun setListeners() {
        edBaseCurrencyAmount.onTextChangeDo {
            currenciesViewModel.setNewBaseAmount(it.toDoubleOrNull())
        }
    }


    private fun getCurrentConvertedValue() {
        currenciesViewModel.getCurrentConvertedValue().observeOn(viewLifecycleOwner, Observer {
            tvConvertedAmount.setNumberTemplateWithSign(it, DEFAULT_DECIMALS)
        })
    }


    private fun getCurrentBaseValue() {
        currenciesViewModel.getCurrentBaseValueLiveData()?.let {
            edBaseCurrencyAmount.setText(it.toString())
        }
    }

    private fun getCurrentCurrency() {
        currenciesViewModel.getSelectedCurrency().observe(viewLifecycleOwner, Observer {
            bindDataToViews(it)
        })

    }



    private fun bindDataToViews(currency: Currency) {
        tvConvertCurrency.text = currency.name
        tvConvertedAmount.setNumberTemplateWithSign(currency.rate, DEFAULT_DECIMALS)
    }


}