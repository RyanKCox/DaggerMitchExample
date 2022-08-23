package com.revature.daggermitchexample.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor(
) : ViewModel(){

    init {
        Log.d("AuthViewModel","ViewModel Created")
    }

}