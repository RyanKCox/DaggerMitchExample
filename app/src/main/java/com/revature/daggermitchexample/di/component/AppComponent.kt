package com.revature.daggermitchexample.di.component

import android.app.Application
import com.revature.daggermitchexample.BaseApplication
import com.revature.daggermitchexample.di.module.ActivityBuilderModule
import com.revature.daggermitchexample.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    AppModule::class
    ])
interface AppComponent: AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(app:Application):Builder

        fun build():AppComponent
    }
}