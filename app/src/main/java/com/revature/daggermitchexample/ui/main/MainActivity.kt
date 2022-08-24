package com.revature.daggermitchexample.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.revature.daggermitchexample.R
import com.revature.daggermitchexample.core.BaseActivity
import com.revature.daggermitchexample.ui.main.post.PostFragment

class MainActivity :BaseActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testFragment()
    }
    private fun testFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer,PostFragment())
            .commit()
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}