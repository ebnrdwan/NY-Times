package com.ebnrdwan.task.data.service


import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {
    companion object {
        private const val MOST_VIEWED_ALL_SECTIONS_PATH = "all-sections/7.json"
        private const val API_KEY_QUERY = "api-key"
    }

    /*============== end-points-start================*/

    @GET(MOST_VIEWED_ALL_SECTIONS_PATH)
    fun getArticlesNews(@Query(API_KEY_QUERY) apiKey: String): Single<ArticlesEntity>

    /*============== end points-end================*/
}
