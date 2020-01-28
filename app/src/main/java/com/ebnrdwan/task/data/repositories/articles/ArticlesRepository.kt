package com.ebnrdwan.task.data.repositories.articles

import android.content.Context
import com.ebnrdwan.task.R
import com.ebnrdwan.task.data.dto.articles.ArticlesEntity
import com.ebnrdwan.core.data.repositories.BaseRepository
import com.ebnrdwan.task.data.sources.articles.IArticlesDataSource
import com.ebnrdwan.core.di.MainArticlesSource
import com.ebnrdwan.corepresentation.utils.NoInternetException
import io.reactivex.Single
import javax.inject.Inject


class ArticlesRepository @Inject constructor(
    @MainArticlesSource private val articlesDataSource: IArticlesDataSource,
    private val mContext: Context
) : BaseRepository(mContext), IArticlesRepository {
    override fun fetchArticles():
            Single<ArticlesEntity> {

        return checkInternetConnection()
            .flatMap { isConnected ->
                if (isConnected) articlesDataSource.getArticles()
                else Single.error(
                    NoInternetException(
                        mContext.resources.getString(
                            R.string.no_internet
                        )
                    )
                )
            }
    }
}

