package com.revature.daggermitchexample.di.auth

import com.revature.daggermitchexample.network.auth.AuthAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class AuthModule {
    @Provides
    fun provideAuthAPI(retrofit: Retrofit):AuthAPI{
        return retrofit.create(AuthAPI::class.java)
    }
}