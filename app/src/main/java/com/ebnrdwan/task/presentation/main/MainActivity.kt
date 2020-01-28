package com.ebnrdwan.task.presentation.main

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
import com.ebnrdwan.corepresentation.base.BaseActivity
import com.ebnrdwan.corepresentation.base.exportToolbar
import com.ebnrdwan.task.data.models.DrawerModel
import com.ebnrdwan.task.presentation.main.drawer.DrawerViewModel
import com.ebnrdwan.corepresentation.utils.changeAppLanguage
import com.ebnrdwan.corepresentation.utils.showToast
import com.ebnrdwan.corepresentation.utils.toggleLanguage
import kotlinx.android.synthetic.main.activity_main.*



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
        setupToolbar(exportToolbar)
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
                changeAppLanguage(
                    this,
                    toggleLanguage()
                )
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
