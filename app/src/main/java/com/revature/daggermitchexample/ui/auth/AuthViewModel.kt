package com.revature.daggermitchexample.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.revature.daggermitchexample.SessionManager
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
    private val sessionManager: SessionManager
) : ViewModel(){

    init {
        Log.d("AuthViewModel","ViewModel Created")
        Log.d("AuthViewModel","AuthAPI - $authAPI")
        Log.d("AuthViewModel","SessionManager - $sessionManager")
    }

    //Provider for use in the AuthActivity
    fun observeAuthState() = sessionManager.getAuthUser()

    /**
     * Attempts to load the user from the passed in user ID.
     *
     * On use, sets the status to loading, then attempts to fetch the user from the API.
     * If the user is fetched correctly, maps the user into an Authenticated status
     * If an error occurs, returns the Error status and a message
     */
    fun authenticateWithId(userId: Int) {

        Log.d("AuthViewModel","Attempting Authenticating user:$userId")

        sessionManager.authenticateWithID(queryUserID(userId))
    }

    private fun queryUserID(userID:Int):LiveData<AuthStatus<User>>{
        return  LiveDataReactiveStreams.fromPublisher(
            authAPI.getUser(userID)
                        .map {
                            AuthStatus.Authenticated(it) as AuthStatus<User>
                        }
                        .onErrorReturn {
                            AuthStatus.Error("Failed, User not in bounds of 1 to 10")
                        }
                        .subscribeOn(Schedulers.io()))
    }

}