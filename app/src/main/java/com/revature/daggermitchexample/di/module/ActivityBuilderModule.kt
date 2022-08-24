package com.revature.daggermitchexample.di.module

import com.revature.daggermitchexample.di.module.auth.AuthModule
import com.revature.daggermitchexample.di.module.auth.AuthViewModelsModule
import com.revature.daggermitchexample.di.module.main.MainFragmentBuilderModule
import com.revature.daggermitchexample.di.module.main.MainModule
import com.revature.daggermitchexample.di.module.main.MainViewModelModule
import com.revature.daggermitchexample.ui.auth.AuthActivity
import com.revature.daggermitchexample.ui.main.MainActivity
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

    @ContributesAndroidInjector(modules = [
        MainFragmentBuilderModule::class,
        MainViewModelModule::class,
        MainModule::class
    ])
    abstract fun contributeMainActivity():MainActivity
}