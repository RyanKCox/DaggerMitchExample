package com.revature.daggermitchexample.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.revature.daggermitchexample.models.User
import com.revature.daggermitchexample.network.auth.AuthAPI
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(
    private val authAPI: AuthAPI,
) : ViewModel(){

    private val authUser = MediatorLiveData<User>()
    init {
        Log.d("AuthViewModel","ViewModel Created")
        Log.d("AuthViewModel","AuthAPI - $authAPI")


    }

    fun observeUser() = authUser

    fun authenticateWithId(userId: Int) {
        val source: LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            authAPI.getUser(userId)
                .subscribeOn(Schedulers.io()))
        authUser.addSource(source
        ) { user ->
            authUser.value = user
            authUser.removeSource(source)
        }
    }

}