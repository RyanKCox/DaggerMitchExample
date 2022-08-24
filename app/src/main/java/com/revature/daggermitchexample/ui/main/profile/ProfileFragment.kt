package com.revature.daggermitchexample.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.revature.daggermitchexample.R
import com.revature.daggermitchexample.models.User
import com.revature.daggermitchexample.ui.auth.AuthStatus
import com.revature.daggermitchexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment :DaggerFragment(){

    private lateinit var username:TextView
    private lateinit var email:TextView
    private lateinit var website:TextView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var viewModel:ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this,providerFactory)[ProfileViewModel::class.java]

        username = view.findViewById(R.id.username)
        email = view.findViewById(R.id.email)
        website = view.findViewById(R.id.website)

        subscribeObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }

    private fun subscribeObservers(){
        viewModel.getAuthState().removeObservers(viewLifecycleOwner)
        viewModel.getAuthState().observe(viewLifecycleOwner){
            authStatus->

            when(authStatus){
                is AuthStatus.Authenticated->{
                    setUserDetails(authStatus.data)
                }
                is AuthStatus.Error->{
                    setErrorDetails(authStatus.error)
                }
                else->{}
            }
        }
    }
    private fun setUserDetails(user: User){
        username.text = getString(R.string.username) + user.userName
        email.text = getString(R.string.email) + user.email
        website.text = getString(R.string.website) +user.website

    }
    private fun setErrorDetails(error:String){
        email.text = error
        username.text = "Error!"
        website.text = "Error!"
    }
}