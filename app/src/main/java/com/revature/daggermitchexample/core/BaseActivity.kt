package com.revature.daggermitchexample.core

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.revature.daggermitchexample.SessionManager
import com.revature.daggermitchexample.ui.auth.AuthActivity
import com.revature.daggermitchexample.ui.auth.AuthStatus
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity:DaggerAppCompatActivity() {
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }
    private fun subscribeObservers(){
        sessionManager.getAuthUser().observe(this){
            authStatus->
            when (authStatus){
                is AuthStatus.Loading->{}
                is AuthStatus.Authenticated -> {
                    Log.d("AuthActivity","Login Success - ${authStatus.data.email}")
                }
                is AuthStatus.Error -> {
                    Log.d("AuthActivity","Login Error - ${authStatus.error}")
                }
                AuthStatus.NotAuthenticated -> {
                    navLoginScreen()
                }
            }
        }
    }
    private fun navLoginScreen(){
        val intent = Intent(this,AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}