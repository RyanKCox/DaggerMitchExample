package com.revature.daggermitchexample.ui.auth

/**
 * Status of Authentication. Contains the authenticated user if Authenticated or error message
 * if Error
 */
sealed class AuthStatus<out T>(){
    object Loading:AuthStatus<Nothing>()
    data class Error(val error:String):AuthStatus<Nothing>()
    data class Authenticated<T>(val data:T):AuthStatus<T>()
    object NotAuthenticated:AuthStatus<Nothing>()
}