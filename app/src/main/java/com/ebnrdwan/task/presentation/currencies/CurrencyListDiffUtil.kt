package com.ebnrdwan.task.presentation.currencies

import androidx.recyclerview.widget.DiffUtil
import com.ebnrdwan.task.data.dto.currencies.Currency

class CurrencyListDiffUtil : DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, currency: Currency): Boolean =
        oldItem.name == currency.name

    override fun areContentsTheSame(oldItem: Currency, currency: Currency): Boolean =
        oldItem == currency
}