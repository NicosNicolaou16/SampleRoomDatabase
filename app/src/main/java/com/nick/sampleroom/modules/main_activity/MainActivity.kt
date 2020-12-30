package com.nick.sampleroom.modules.main_activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.nick.sampleroom.R
import com.nick.sampleroom.databinding.ActivityMainBinding
import com.nick.sampleroom.utils.base_classes.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding?.root)

        initNavigationToFragment()
    }

    private fun initNavigationToFragment() {
        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}