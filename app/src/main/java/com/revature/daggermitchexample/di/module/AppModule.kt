package com.revature.daggermitchexample.di.module

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.revature.daggermitchexample.R
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideRequestOptions():RequestOptions{
        return RequestOptions.placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)
    }
    @Provides
    fun provideRequestManager(
        app:Application,
        requestOptions: RequestOptions
    ):RequestManager{
        return Glide.with(app)
            .setDefaultRequestOptions(requestOptions)
    }
    @Provides
    fun provideAppDrawable(app:Application): Drawable {
        return ContextCompat.getDrawable(app,R.drawable.logo)!!
    }
}