package com.revature.daggermitchexample.di.module.main

import androidx.lifecycle.ViewModel
import com.revature.daggermitchexample.di.ViewModelKey
import com.revature.daggermitchexample.ui.main.post.PostViewModel
import com.revature.daggermitchexample.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileVM(viewModel: ProfileViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostVM(viewModel: PostViewModel):ViewModel
}