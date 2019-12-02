package com.example.calcirkotlin.ui.Principal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.calcirkotlin.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class App_Logado : AppCompatActivity() {
    var navView: BottomNavigationView? = null
    var appBarConfiguration: AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)
        navView = findViewById<BottomNavigationView?>(R.id.nav_view)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_movi, R.id.navigation_media)
            .build()

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration!!)
        NavigationUI.setupWithNavController(this!!.navView!!, navController)
    }
}