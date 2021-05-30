package com.ebnrdwan.task.presentation.currencies

import androidx.recyclerview.widget.DiffUtil
import com.ebnrdwan.task.data.dto.currencies.Currency

class CurrencyListDiffUtil : DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, articleItemItem: Currency): Boolean =
        oldItem.name == articleItemItem.name

    override fun areContentsTheSame(oldItem: Currency, articleItemItem: Currency): Boolean =
        oldItem == articleItemItem
}