package com.revature.daggermitchexample.ui.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.revature.daggermitchexample.SessionManager
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
) :ViewModel(){

    init {
        Log.d("ProfileVM","ViewModel is ready")
    }

    fun getAuthState() = sessionManager.getAuthUser()
}