package com.revature.daggermitchexample.di.module

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun someString() = "This is a test String"

    @Provides
    fun getApp(app:Application):Boolean{
        return app != null
    }
}