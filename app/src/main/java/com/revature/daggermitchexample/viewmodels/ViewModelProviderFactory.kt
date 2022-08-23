package com.revature.daggermitchexample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * ViewModelProviderFactory -
 * Allows for creation of ViewModels with Dagger Injection.
 *
 * Input
 *      creators
 *          key - Class that overrides the ViewModel class
 *          value - Injectable Provider for ViewModels - must use JvmSuppressWildcards
 */
class ViewModelProviderFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    /**
     * create -
     * Creates the ViewModel class of the given type.
     *
     * First - Checks if the ViewModel has been created already
     * Second - Checks if the given class is registered in the Dagger Map to be created
     * Third - Throws IllegalArgumentException if class is not in the Dagger Map
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        @Suppress("UNCHECKED_CAST")
        return creator.get() as T
    }
}
