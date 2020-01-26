package com.ebnrdwan.task.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.ebnrdwan.task.R
import com.ebnrdwan.task.ui.base.BaseActivity
import com.ebnrdwan.task.data.models.DrawerModel
import com.ebnrdwan.task.ui.main.drawer.DrawerViewModel
import com.ebnrdwan.task.util.changeAppLanguage
import com.ebnrdwan.task.util.showToast
import com.ebnrdwan.task.util.toggleLanguage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var drawerToggle: ActionBarDrawerToggle
    lateinit var drawerViewModel: DrawerViewModel

    override fun getLayout(): Int {
        return R.layout.activity_main
    }


    /*============ initializeScreenComponents-start================*/
    override fun initializeScreenComponents() {
        initializeViews()
        initializeViewModel()
        observeOnDrawerClicks()
    }

    /*============ initializeScreenComponents-end================*/

    private fun initializeViews() {
        setupToolbar(toolbar)
        navController = findNavController(R.id.nav_host_fragment)
        configureDrawerMenu()
    }

    private fun initializeViewModel() {
        drawerViewModel = ViewModelProvider(this).get(DrawerViewModel::class.java)
    }

    private fun observeOnDrawerClicks() {
        drawerViewModel.getDrawerItemClicked().observe(this, Observer {
            onDrawerItemSelected(it)
        })
    }

    /*============ configureDrawerMenu-start================*/
    private fun configureDrawerMenu() {
        setupAppbarConfiguration()
        createDrawerToggle()
    }

    private fun createDrawerToggle() {
        drawerToggle =
            ActionBarDrawerToggle(
                this,
                drawer_layout,
                R.string.open,
                R.string.close
            )
        drawerToggle.syncState()
    }

    private fun setupAppbarConfiguration() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        appBarConfiguration = AppBarConfiguration(
            navController.graph
            , drawer_layout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    /*============ configureDrawerMenu-end================*/

    /*============ onDrawerItemSelected-start================*/
    private fun onDrawerItemSelected(item: DrawerModel) {
        drawer_layout.closeDrawer(GravityCompat.START)
        when (item.name) {
            R.string.menu_language -> {
                changeAppLanguage(this, toggleLanguage())
            }
            else -> {
                showToast(getString(item.name))
            }
        }
    }
    /*============ onDrawerItemSelected-end================*/

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }


}
