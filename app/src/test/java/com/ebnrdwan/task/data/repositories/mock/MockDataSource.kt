package com.ebnrdwan.task.data.repositories.mock

import com.ebnrdwan.task.data.dto.articles.ArticleItem
import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.ebnrdwan.task.util.Constants.articlesItemResponseFileName
import com.ebnrdwan.task.util.Constants.articlesResponseFileName
import com.ebnrdwan.task.util.loadJsonFromResources
import com.google.gson.Gson

object MockDataSource {

    fun getFakeArticlesResponse(): ArticlesEntity {
        val json = loadJsonFromResources(articlesResponseFileName)
        return Gson().fromJson(json, ArticlesEntity::class.java)
    }


    fun getFakeArticleItem(): ArticleItem {
        val json = loadJsonFromResources(articlesItemResponseFileName)
        return Gson().fromJson(json, ArticleItem::class.java)
    }


}