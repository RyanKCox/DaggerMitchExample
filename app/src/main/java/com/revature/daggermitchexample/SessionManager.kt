package com.revature.daggermitchexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.revature.daggermitchexample.models.User
import com.revature.daggermitchexample.ui.auth.AuthStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val cachedUser = MediatorLiveData<AuthStatus<User>>()

    fun authenticateWithID(source:LiveData<AuthStatus<User>>){
        cachedUser.value = AuthStatus.Loading
        cachedUser.addSource(source){
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }
    fun logOut(){
        Log.d("SessionManager","Logging out")
        cachedUser.value = AuthStatus.NotAuthenticated
    }
    fun getAuthUser() = cachedUser
}