package com.ebnrdwan.task.data.repositories.mock

import com.ebnrdwan.task.data.dto.articles.ArticleItem
import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.google.gson.Gson

object MockDataSource {

    fun getFakeArticlesResponse(): ArticlesEntity {
        val mockedObject = MockArticleResponse()
        val gson = Gson()
        val json = gson.toJson(mockedObject)
        return gson.fromJson(json, ArticlesEntity::class.java)
    }


    fun getFakeArticleItem(): ArticleItem {
        val mockedObject = MockResult()
        val gson = Gson()
        val json = gson.toJson(mockedObject)
        return gson.fromJson(json, ArticleItem::class.java)
    }
}