package com.ebnrdwan.task.presentation.articles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ebnrdwan.task.R
import com.ebnrdwan.task.data.dto.articles.ArticleItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_list_item.view.*


class ArticlesAdapterPaged
constructor(
    val context: Context,
    private var articles: List<ArticleItem>,
    val onClickCallback: (ArticleItem) -> Unit
) :
    PagedListAdapter<ArticleItem, ArticlesAdapterPaged.ViewHolder>(
        ArticlesDiffUtil()
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


    fun updateArticles(articleItemArticles: List<ArticleItem>) {
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
        fun bind(article: ArticleItem) {
            rootView.tv_title.text = article.title
            rootView.tv_creation_date.text = article.publishedDate
            rootView.tv_likes.text =
                String.format(context.resources.getString(R.string.type), article.type)
            rootView.tv_views.text =
                String.format(context.resources.getString(R.string.views), article.views)
            article.getFirstMedia()?.let {
                Picasso.get().load(it).into(rootView.iv_post_cover)

            }

            rootView.setOnClickListener {
                onClickCallback(article)
            }
        }


    }

}