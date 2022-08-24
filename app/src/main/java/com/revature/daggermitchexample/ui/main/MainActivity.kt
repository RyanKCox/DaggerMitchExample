package com.revature.daggermitchexample.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.revature.daggermitchexample.R
import com.revature.daggermitchexample.core.BaseActivity

class MainActivity :
    BaseActivity(),
    NavigationView.OnNavigationItemSelectedListener
{

    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navView:NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        setupNav()

    }

    private fun setupNav(){
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(navView,navController)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout ->{
                sessionManager.logOut()
                true
            }
            android.R.id.home->{
                if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile->{
                Navigation.findNavController(this,R.id.nav_host_fragment)
                    .popBackStack(R.id.profileScreen,false)
//                    .navigate(R.id.profileScreen)
            }
            R.id.nav_post->{
                if(isValidDestination(R.id.postsScreen)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.postsScreen)
                }
            }
        }
        item.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun isValidDestination(destination:Int) =
        destination !=
                Navigation.findNavController(
                    this,
                    R.id.nav_host_fragment)
                    .currentDestination!!.id

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this,R.id.nav_host_fragment),
            drawerLayout
        )
    }

    override fun onBackPressed() {
        if(!Navigation.findNavController(this,R.id.nav_host_fragment)
                .popBackStack()) {
            sessionManager.logOut()
        }
    }
}