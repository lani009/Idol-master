package com.wimi.idolmaster.ui.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.ActivityMainBinding
import com.wimi.idolmaster.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class
) {

    override fun onCreate() {
        val host = supportFragmentManager.findFragmentById(R.id.navHostfragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigation, host.navController)

        bottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
    }

}
