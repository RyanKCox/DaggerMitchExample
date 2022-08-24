package com.revature.daggermitchexample.di.module.auth

import androidx.lifecycle.ViewModel
import com.revature.daggermitchexample.di.ViewModelKey
import com.revature.daggermitchexample.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    /**
     * Bind the AuthViewModel to the Dagger Map
     */
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthVM(viewModel:AuthViewModel): ViewModel
}