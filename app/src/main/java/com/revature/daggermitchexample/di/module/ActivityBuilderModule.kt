package com.revature.daggermitchexample.di.module

import com.revature.daggermitchexample.di.auth.AuthViewModelsModule
import com.revature.daggermitchexample.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule{

    /**
     * Dagger Subcomponent creator using @ContributesAndroidInjector
     * for the AuthActivity. Uses the AuthViewModelsModule
     */
    @ContributesAndroidInjector(modules =
    [AuthViewModelsModule::class])
    abstract fun contributeAuthActivity(): AuthActivity
}