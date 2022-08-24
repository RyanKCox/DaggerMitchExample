package com.revature.daggermitchexample.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.revature.daggermitchexample.R
import com.revature.daggermitchexample.ui.main.MainActivity
import com.revature.daggermitchexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Authentication Activity / Login Screen
 */
class AuthActivity : DaggerAppCompatActivity() {

    //Dagger provided Injectables for displaying Glide images and setting up the viewmodel
    @Inject
    lateinit var logo:Drawable
    @Inject
    lateinit var requestManager: RequestManager
    @Inject
    lateinit var providerFactory:ViewModelProviderFactory

    private lateinit var viewModel:AuthViewModel
    private lateinit var userID:EditText
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Assign variables to their xml attributes
        userID = findViewById(R.id.user_id_input)
        progressBar = findViewById(R.id.progress_bar)

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

    /**
     * Subscribes the the authUser from the viewmodel and displays based on it's status
     */
    private fun subscribeObserver(){
        viewModel.observeAuthState()
            .observe(this
        ) { authStatus ->
                when (authStatus){
                    is AuthStatus.Loading->{showProgress(true)}
                    is AuthStatus.Authenticated -> {
                        showProgress(false)
                        Log.d("AuthActivity","Login Success - ${authStatus.data.email}")
                        onLoginSuccess()
                    }
                    is AuthStatus.Error -> {
                        showProgress(false)

                        Toast.makeText(
                            this,
                            "Login Failed! Did you enter an ID between 1 and 10?",
                            Toast.LENGTH_LONG).show()

                        Log.d("AuthActivity","Login Error - ${authStatus.error}")
                    }
                    AuthStatus.NotAuthenticated -> {showProgress(false)}
                }
        }
    }
    private fun showProgress(isVisible:Boolean){
        if(isVisible)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.GONE
    }

    private fun onLoginSuccess(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}