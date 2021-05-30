package com.ebnrdwan.task.data.repositories.mock

import com.ebnrdwan.task.data.dto.currencies.ArticleItem
import com.ebnrdwan.task.data.dto.currencies.CurrenciesResponse
import com.ebnrdwan.task.util.Constants.articlesItemResponseFileName
import com.ebnrdwan.task.util.Constants.articlesResponseFileName
import com.ebnrdwan.task.util.loadJsonFromResources
import com.google.gson.Gson

object MockDataSource {

    fun getFakeArticlesResponse(): CurrenciesResponse {
        val json = loadJsonFromResources(articlesResponseFileName)
        return Gson().fromJson(json, CurrenciesResponse::class.java)
    }


    fun getFakeArticleItem(): ArticleItem {
        val json = loadJsonFromResources(articlesItemResponseFileName)
        return Gson().fromJson(json, ArticleItem::class.java)
    }


}