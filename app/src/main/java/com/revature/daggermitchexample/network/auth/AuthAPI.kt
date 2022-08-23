package com.revature.daggermitchexample.network.auth

import com.revature.daggermitchexample.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthAPI {
    @GET("/users/{id}")
    fun getUser(@Path("id") id:Int ): Flowable<User>
}