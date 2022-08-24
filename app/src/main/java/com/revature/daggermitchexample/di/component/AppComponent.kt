package com.revature.daggermitchexample.di.component

import android.app.Application
import com.revature.daggermitchexample.BaseApplication
import com.revature.daggermitchexample.SessionManager
import com.revature.daggermitchexample.di.module.ActivityBuilderModule
import com.revature.daggermitchexample.di.module.AppModule
import com.revature.daggermitchexample.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Application level component for Dagger Graph
 *
 * Provides an instance of the Application to the Dagger Graph
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    AppModule::class,
    ViewModelFactoryModule::class
    ])
interface AppComponent: AndroidInjector<BaseApplication> {

    fun sessionManager():SessionManager

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(app:Application):Builder

        fun build():AppComponent
    }
}