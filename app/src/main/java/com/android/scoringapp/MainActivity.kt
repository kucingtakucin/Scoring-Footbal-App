package com.android.scoringapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.scoringapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        dataBinding.apply {
            setSupportActionBar(toolbarMain)
            drawerLayout = drawerLayoutContainer
            navController = findNavController(R.id.nav_host_fragment)
            appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
            toolbarMain.setupWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
            bottomNavigation.setupWithNavController(navController)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) or super.onSupportNavigateUp()
    }
}