package com.revature.daggermitchexample.di.module.main

import com.revature.daggermitchexample.network.main.MainAPI
import com.revature.daggermitchexample.ui.main.post.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    @Provides
    fun provideMainApi(retrofit: Retrofit):MainAPI{
        return retrofit.create(MainAPI::class.java)
    }

    @Provides
    fun providePostRecycler():PostsRecyclerAdapter{
        return PostsRecyclerAdapter()
    }
}