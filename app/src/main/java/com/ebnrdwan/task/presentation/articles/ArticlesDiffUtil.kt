package com.ebnrdwan.task.presentation.articles

import androidx.recyclerview.widget.DiffUtil
import com.ebnrdwan.task.data.dto.articles.ArticleItem

class ArticlesDiffUtil : DiffUtil.ItemCallback<ArticleItem>() {

    override fun areItemsTheSame(oldItem: ArticleItem, articleItemItem: ArticleItem): Boolean =
        oldItem.title == articleItemItem.title

    override fun areContentsTheSame(oldItem: ArticleItem, articleItemItem: ArticleItem): Boolean =
        oldItem == articleItemItem
}