package com.revature.daggermitchexample.di.module.auth

import com.revature.daggermitchexample.di.scope.AuthScope
import com.revature.daggermitchexample.network.auth.AuthAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {
    @AuthScope
    @Provides
    fun provideAuthAPI(retrofit: Retrofit):AuthAPI{
        return retrofit.create(AuthAPI::class.java)
    }
}