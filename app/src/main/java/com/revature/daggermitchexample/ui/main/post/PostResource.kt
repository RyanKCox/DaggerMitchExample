package com.revature.daggermitchexample.ui.main.post

import com.revature.daggermitchexample.models.Post

sealed class PostResource<out T>(){
    object Loading:PostResource<Nothing>()
    data class DisplayPosts<T>(val posts:List<T>):PostResource<T>()
    data class Error(val error:String):PostResource<Nothing>()
}