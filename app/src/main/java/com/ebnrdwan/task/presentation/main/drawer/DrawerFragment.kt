package com.ebnrdwan.task.presentation.main.drawer

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ebnrdwan.task.R
import com.ebnrdwan.corepresentation.base.BaseFragment
import com.ebnrdwan.task.data.models.DrawerModel
import kotlinx.android.synthetic.main.drawer_fragment.*

class DrawerFragment : BaseFragment() {

    private var viewModel: DrawerViewModel? = null
    override fun getLayout(): Int = R.layout.drawer_fragment


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it).get(DrawerViewModel::class.java) }
        setDrawerItemsListeners()
    }

    private fun setDrawerItemsListeners() {
        menu_articles.setOnClickListener(onDrawerClickListener)
        menu_map.setOnClickListener(onDrawerClickListener)
        menu_events.setOnClickListener(onDrawerClickListener)
        menu_leadership.setOnClickListener(onDrawerClickListener)
        menu_language.setOnClickListener(onDrawerClickListener)
    }


    override fun observeOnUiState() {
    }

    private val onDrawerClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.menu_articles -> onDrawerItemSelected(DrawerModel.getDrawerMenu()[0])
            R.id.menu_map -> onDrawerItemSelected(DrawerModel.getDrawerMenu()[1])
            R.id.menu_events -> onDrawerItemSelected(DrawerModel.getDrawerMenu()[2])
            R.id.menu_leadership -> onDrawerItemSelected(DrawerModel.getDrawerMenu()[3])
            R.id.menu_language -> onDrawerItemSelected(DrawerModel.getDrawerMenu()[4])
        }
    }

    /*============ onDrawerItemSelected-start================*/
    private fun onDrawerItemSelected(item: DrawerModel) {
        viewModel?.setDrawerItemClicked(item)
    }
    /*============ onDrawerItemSelected-end================*/
}