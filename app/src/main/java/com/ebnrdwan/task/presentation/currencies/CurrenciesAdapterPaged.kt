package com.ebnrdwan.task.presentation.currencies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ebnrdwan.task.R
import com.ebnrdwan.task.data.dto.currencies.Currency
import com.ebnrdwan.task.util.Constants
import kotlinx.android.synthetic.main.article_list_item.view.*
import kotlinx.android.synthetic.main.fragment_conversion.*
import setNumberTemplateWithSign


class CurrenciesAdapterPaged
constructor(
    val context: Context,
    private var articles: List<Currency>,
    val onClickCallback: (Currency) -> Unit
) :
    PagedListAdapter<Currency, CurrenciesAdapterPaged.ViewHolder>(
        CurrencyListDiffUtil()
    ) {


    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView: View =
            LayoutInflater.from(context)
                .inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(rootView)
    }

    // binds the drawerList to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        article.let { holder.bind(article) }
    }


    fun updateArticles(articleItemArticles: List<Currency>) {
        articles = articleItemArticles
        notifyDataSetChanged()
    }

    // total number of rows
    override fun getItemCount(): Int {
        return articles.size
    }


    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(private val rootView: View) :
        RecyclerView.ViewHolder(rootView) {
        init {
            rootView.setOnClickListener { onClickCallback.invoke(articles[adapterPosition]) }
        }
        fun bind(currency: Currency) {
            rootView.tv_title.text = currency.name
            rootView.tvRate.setNumberTemplateWithSign(currency.rate, Constants.Ui.DEFAULT_DECIMALS)
        }


    }

}