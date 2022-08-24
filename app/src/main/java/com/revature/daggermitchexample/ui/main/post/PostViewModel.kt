package com.revature.daggermitchexample.ui.main.post

import android.util.Log
import androidx.lifecycle.*
import com.revature.daggermitchexample.SessionManager
import com.revature.daggermitchexample.models.Post
import com.revature.daggermitchexample.network.main.MainAPI
import com.revature.daggermitchexample.ui.auth.AuthStatus
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainAPI: MainAPI
) :ViewModel(){

    private val posts = MediatorLiveData<PostResource<Post>>()

    init {
        Log.d("PostViewModel","ViewModel is working")
    }

    fun observePosts() : LiveData<PostResource<Post>>{
        posts.value = PostResource.Loading

        val status = sessionManager.getAuthUser().value
        val source = if(status is AuthStatus.Authenticated){
            LiveDataReactiveStreams.fromPublisher(
                mainAPI.fetchPosts((status.data.id))
                    .map {
                        PostResource.DisplayPosts(it) as PostResource<Post>
                    }
                    .onErrorReturn {
                        PostResource.Error(it.message ?: "Error on Loading")
                    }
                    .subscribeOn(Schedulers.io()))
        }
        else{
            MutableLiveData<PostResource<Post>>(PostResource.Error("Authentication failed"))
        }

        posts.addSource(source){
            posts.value = it
            posts.removeSource(source)
        }
        return posts
    }


}