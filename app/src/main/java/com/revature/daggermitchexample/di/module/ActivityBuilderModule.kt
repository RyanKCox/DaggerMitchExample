package com.revature.daggermitchexample.di.module

import com.revature.daggermitchexample.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule{

    @ContributesAndroidInjector
    abstract fun contributeAuthActivity():AuthActivity
}