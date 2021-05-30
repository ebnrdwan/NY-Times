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

    override fun getLayout(): Int {
        return R.layout.activity_main
    }


    /*============ initializeScreenComponents-start================*/
    override fun initializeScreenComponents() {
    }




}
