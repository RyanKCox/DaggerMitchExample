package com.revature.daggermitchexample.ui.auth


sealed class AuthStatus<out T>(){
    object Loading:AuthStatus<Nothing>()
    data class Error(val error:String):AuthStatus<Nothing>()
    data class Authenticated<T>(val data:T):AuthStatus<T>()
    object NotAuthenticated:AuthStatus<Nothing>()
}


//    enum class AuthStatus {
//        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
//    }
//
//    companion object {
//        fun <T> authenticated(data: T?): AuthResource<T?> {
//            return AuthResource(AuthStatus.AUTHENTICATED, data, null)
//        }
//
//        fun <T> error(msg: String, data: T?): AuthResource<T?> {
//            return AuthResource(AuthStatus.ERROR, data, msg)
//        }
//
//        fun <T> loading(data: T?): AuthResource<T?> {
//            return AuthResource(AuthStatus.LOADING, data, null)
//        }
//
//        fun <T> logout(): AuthResource<T?> {
//            return AuthResource<Any?>(AuthStatus.NOT_AUTHENTICATED, null, null)
//        }
//    }
//}