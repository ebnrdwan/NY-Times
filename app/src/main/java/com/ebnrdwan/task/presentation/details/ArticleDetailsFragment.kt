package com.ebnrdwan.task.presentation.details

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.ebnrdwan.task.R
import com.ebnrdwan.task.data.dto.articles.ArticleItem
import com.ebnrdwan.task.presentation.ApplicationController
import com.ebnrdwan.task.presentation.articles.ArticlesViewModel
import com.ebnrdwan.corepresentation.base.BaseFragment
import com.ebnrdwan.corepresentation.utils.fade
import com.ebnrdwan.corepresentation.utils.translateFromStart
import com.ebnrdwan.corepresentation.utils.translaterFromEnd
import com.ebnrdwan.corepresentation.utils.translaterFromUp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class ArticleDetailsFragment : BaseFragment() {


    private val _duration1500: Long = 1500
    private val _duration1000: Long = 1000
    private val _delay500: Long = 500
    private val _delay1000: Long = 1000


    private val args: ArticleDetailsFragmentArgs by navArgs()
    override fun getLayout(): Int = R.layout.fragment_detail


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val articlesViewModel by activityViewModels<ArticlesViewModel> { viewModelFactory }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            (it.application as ApplicationController)
                .appComponent.registerArticleComponent()
                .create()
                .inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchArticlesDetails()
    }


    private fun fetchArticlesDetails() {
        articlesViewModel.getSelectedArticle().observe(viewLifecycleOwner, Observer {

            bindDataToViews(it)
        })

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun bindDataToViews(article: ArticleItem) {

        tv_likes.text = String.format(resources.getString(R.string.type), article.type)
        tv_views.text =
            String.format(resources.getString(R.string.views), article.views)
        tv_description.text = article.abstract
        tv_title.text = article.title
        tv_creation_date.text = article.publishedDate

        article.getFirstMedia()?.let {
            Picasso.get().load(it).into(iv_cover)
        }
        tv_title.translaterFromUp(delay = _delay500, duration = _duration1500)
        tv_creation_date.translateFromStart(delay = _delay1000, duration = _duration1000)
        tv_views.translaterFromEnd(delay = _delay1000, duration = _duration1000)
        tv_likes.fade(delay = _delay1000, duration = _duration1000)

    }


}