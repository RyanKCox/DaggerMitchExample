package com.revature.daggermitchexample.di.module.main

import com.revature.daggermitchexample.ui.main.post.PostFragment
import com.revature.daggermitchexample.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment():ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment():PostFragment
}