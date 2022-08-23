package com.revature.daggermitchexample.di.module

import androidx.lifecycle.ViewModelProvider
import com.revature.daggermitchexample.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    /**
     * Binds the custom ViewModel Factory to be injected
     */
    @Binds
    abstract fun bindViewModelFactory(
        vmFactoryProvider:ViewModelProviderFactory
    ):ViewModelProvider.Factory
}