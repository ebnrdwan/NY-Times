package com.ebnrdwan.task.data.dto.articles

import com.google.gson.annotations.SerializedName

data class ArticleItem(
    @SerializedName("abstract")
    val `abstract`: String = "", // No, Virginia, there is no class war.
    @SerializedName("adx_keywords")
    val adxKeywords: String = "", // Productivity;Wages and Salaries;Income Inequality;Labor and Jobs;Sanders, Bernard
    @SerializedName("asset_id")
    val assetId: Long = 0, // 100000006925786
    @SerializedName("byline")
    val byline: String = "", // By DAVID BROOKS
    @SerializedName("column")
    val column: String = "", // Trilobites
    @SerializedName("id")
    val id: Long = 0, // 100000006925786
    @SerializedName("media")
    val  media: List<Media> = listOf(),
    @SerializedName("published_date")
    val publishedDate: String = "", // 2020-01-16
    @SerializedName("section")
    val section: String = "", // Opinion
    @SerializedName("source")
    val source: String = "", // The New York Times
    @SerializedName("title")
    val title: String = "", // The Bernie Sanders Fallacy
    @SerializedName("type")
    val type: String = "", // Article
    @SerializedName("url")
    val url: String = "", // https://www.nytimes.com/2020/01/16/opinion/the-bernie-sanders-fallacy.html
    @SerializedName("views")
    val views: Int = 0 // 20
){

    fun getFirstMedia():String?{
        return if (media.isNotEmpty() && media[0].mediaMetadata.isNotEmpty() &&
            media[0].mediaMetadata[0].url.isNotEmpty()
        ) media[0].mediaMetadata[0].url
        else null

    }
}

data class Media(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int = 0, // 1
    @SerializedName("caption")
    val caption: String = "",
    @SerializedName("copyright")
    val copyright: String = "", // Ruth Fremson/The New York Times
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata> = listOf(),
    @SerializedName("subtype")
    val subtype: String = "", // photo
    @SerializedName("type")
    val type: String = "" // image
)

data class MediaMetadata(
    @SerializedName("format")
    val format: String = "", // mediumThreeByTwo440
    @SerializedName("height")
    val height: Int = 0, // 293
    @SerializedName("url")
    val url: String = "", // https://static01.nyt.com/images/2020/01/16/opinion/16brooksWeb/16brooksWeb-mediumThreeByTwo440.jpg
    @SerializedName("width")
    val width: Int = 0 // 440
)