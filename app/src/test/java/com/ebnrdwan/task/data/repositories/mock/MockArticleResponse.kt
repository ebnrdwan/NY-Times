package com.ebnrdwan.task.data.repositories.mock

import com.google.gson.annotations.SerializedName


data class MockArticleResponse(
    @SerializedName("copyright")
    val copyright: String = "Copyright (c) 2019 The New York Times Company.  All Rights Reserved.", // Copyright (c) 2019 The New York Times Company.  All Rights Reserved.
    @SerializedName("num_results")
    val numResults: Int = 1672, // 1672
    @SerializedName("mockResults")
    val mockResults: List<MockResult> = listOf(MockResult()),
    @SerializedName("status")
    val status: String = "OK" // OK
)

data class MockResult(
    @SerializedName("abstract")
    val `abstract`: String = "Pope Francis’ acknowledgment of the problem came after decades of allegations and seeming Vatican inaction, and arrived at a time of heightened awareness of sexual abuse in the #MeToo era.", // Pope Francis’ acknowledgment of the problem came after decades of allegations and seeming Vatican inaction, and arrived at a time of heightened awareness of sexual abuse in the #MeToo era.
    @SerializedName("adx_keywords")
    val adxKeywords: String = "Priests;Nuns;Francis;Roman Catholic Church;Sex Crimes;#MeToo Movement;Women and Girls;Abortion;Women Church World (Magazine)", // Priests;Nuns;Francis;Roman Catholic Church;Sex Crimes;#MeToo Movement;Women and Girls;Abortion;Women Church World (Magazine)
    @SerializedName("asset_id")
    val assetId: Long = 100000006345543, // 100000006345543
    @SerializedName("byline")
    val byline: String = "By JASON HOROWITZ", // By JASON HOROWITZ
    @SerializedName("column")
    val column: String = "", // The Long Run
    @SerializedName("des_facet")
    val desFacet: List<String> = listOf(),
    @SerializedName("geo_facet")
    val geoFacet: String = "",
    @SerializedName("id")
    val id: Long = 100000006345543, // 100000006345543
    @SerializedName("media")
    val media: List<Media> = listOf(Media()),
    @SerializedName("org_facet")
    val orgFacet: List<String> = listOf("orgfact","orgfact"),
    @SerializedName("per_facet")
    val perFacet: List<String> = listOf(),
    @SerializedName("published_date")
    val publishedDate: String = "2019-02-06", // 2019-02-06
    @SerializedName("section")
    val section: String = "World", // World
    @SerializedName("source")
    val source: String = "The New York Times", // The New York Times
    @SerializedName("title")
    val title: String = "Sexual Abuse of Nuns: Longstanding Church Scandal Emerges From Shadows", // Sexual Abuse of Nuns: Longstanding Church Scandal Emerges From Shadows
    @SerializedName("type")
    val type: String = "Article", // Article
    @SerializedName("url")
    val url: String = "https://www.nytimes.com/2019/02/06/world/europe/pope-francis-sexual-abuse-nuns.html", // https://www.nytimes.com/2019/02/06/world/europe/pope-francis-sexual-abuse-nuns.html
    @SerializedName("views")
    val views: Int = 20 // 20
)

data class Media(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int = 1, // 1
    @SerializedName("caption")
    val caption: String = "Pope Francis meeting with nuns last year at the Vatican.", // Pope Francis meeting with nuns last year at the Vatican.
    @SerializedName("copyright")
    val copyright: String = "Vincenzo Pinto/Agence France-Presse &mdash; Getty Images", // Vincenzo Pinto/Agence France-Presse &mdash; Getty Images
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata> = listOf(),
    @SerializedName("subtype")
    val subtype: String = "photo", // photo
    @SerializedName("type")
    val type: String = "image" // image
)

data class MediaMetadata(
    @SerializedName("format")
    val format: String = "mediumThreeByTwo440", // mediumThreeByTwo440
    @SerializedName("height")
    val height: Int = 293, // 293
    @SerializedName("url")
    val url: String = "https://static01.nyt.com/images/2019/02/07/world/07nuns-2-print/merlin_142649790_d03865ba-c3e8-4d60-8148-fc6587f4c462-mediumThreeByTwo440.jpg", // https://static01.nyt.com/images/2019/02/07/world/07nuns-2-print/merlin_142649790_d03865ba-c3e8-4d60-8148-fc6587f4c462-mediumThreeByTwo440.jpg
    @SerializedName("width")
    val width: Int = 440 // 440
)