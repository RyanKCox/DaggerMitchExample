package com.revature.daggermitchexample.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.revature.daggermitchexample.R
import com.revature.daggermitchexample.models.User
import com.revature.daggermitchexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var logo:Drawable
    @Inject
    lateinit var requestManager: RequestManager
    @Inject
    lateinit var providerFactory:ViewModelProviderFactory

    private lateinit var viewModel:AuthViewModel
    private lateinit var userID:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        userID = findViewById(R.id.user_id_input)

        findViewById<Button>(R.id.login_button).setOnClickListener {
            attemptLogin()
        }

        viewModel = ViewModelProvider(
            this,
            providerFactory)[AuthViewModel::class.java]

        setLogo()
        subscribeObserver()

    }
    private fun attemptLogin(){
        if(userID.text.isEmpty())
            return
        viewModel.authenticateWithId(userID.text.toString().toInt())
    }

    private fun setLogo(){
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }
    private fun subscribeObserver(){
        viewModel.observeUser()
            .observe(this
        ) { user ->
            if (user != null)
                Log.d("AuthActivity", "onChanged ${user.email}")
        }
    }
}