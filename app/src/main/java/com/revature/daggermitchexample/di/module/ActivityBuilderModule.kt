package com.revature.daggermitchexample.di.module

import com.revature.daggermitchexample.di.auth.AuthModule
import com.revature.daggermitchexample.di.auth.AuthViewModelsModule
import com.revature.daggermitchexample.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule{

    /**
     * Dagger Subcomponent creator using @ContributesAndroidInjector
     * for the AuthActivity Subcomponent
     */
    @ContributesAndroidInjector(modules = [
        AuthViewModelsModule::class,
        AuthModule::class
    ])
    abstract fun contributeAuthActivity(): AuthActivity
}