package com.revature.daggermitchexample.network.main

import com.revature.daggermitchexample.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainAPI {

    @GET("posts")
    fun fetchPosts(@Query("userId")id:Int):Flowable<List<Post>>
}