package com.revature.daggermitchexample.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.revature.daggermitchexample.models.User
import com.revature.daggermitchexample.network.auth.AuthAPI
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewModel for the AuthActivity
 *
 * handles fetching of user data
 *
 *
 */
class AuthViewModel @Inject constructor(
    private val authAPI: AuthAPI,
) : ViewModel(){

    // Livedata observer of the current user status. Contains the user if status is Authenticated
    private val authUser = MediatorLiveData<AuthStatus<User>>()

    init {
        Log.d("AuthViewModel","ViewModel Created")
        Log.d("AuthViewModel","AuthAPI - $authAPI")
    }

    //Provider for use in the AuthActivity
    fun observeUser() = authUser

    /**
     * Attempts to load the user from the passed in user ID.
     *
     * On use, sets the status to loading, then attempts to fetch the user from the API.
     * If the user is fetched correctly, maps the user into an Authenticated status
     * If an error occurs, returns the Error status and a message
     */
    fun authenticateWithId(userId: Int) {

        authUser.value = AuthStatus.Loading

        val source: LiveData<AuthStatus<User>> = LiveDataReactiveStreams.fromPublisher(
            authAPI.getUser(userId)
                .map {
                    AuthStatus.Authenticated(it) as AuthStatus<User>
                }
                .onErrorReturn {
                    AuthStatus.Error("Failed, User not in bounds of 1 to 10")
                }
                .subscribeOn(Schedulers.io()))

        authUser.addSource(source
        ) { authStatus ->
            authUser.value = authStatus
            authUser.removeSource(source)
        }
    }

}