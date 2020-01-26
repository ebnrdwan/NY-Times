package com.ebnrdwan.task.data.dto.articles

import com.google.gson.annotations.SerializedName

data class ArticlesEntity(
    @SerializedName("copyright")
    val copyright: String = "", // Copyright (c) 2020 The New York Times Company.  All Rights Reserved.
    @SerializedName("num_results")
    val numResults: Int = 0, // 1622
    @SerializedName("results")
    val news: List<ArticleItem> = emptyList(),
    @SerializedName("status")
    val status: String = "" // OK
)

