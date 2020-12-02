package com.frankito.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import androidx.navigation.NavController
import java.lang.ref.WeakReference

abstract class BaseViewModel : ViewModel() {
    internal val arguments: MutableLiveData<Bundle> = MutableLiveData()

    // Navigation handling
    private var internalNavController: WeakReference<NavController>? = null

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    val navController: NavController?
        get() = internalNavController?.get()

    fun registerNavController(controller: NavController) {
        internalNavController = WeakReference(controller)
    }

    @CallSuper
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override fun onCleared() {
        super.onCleared()
        internalNavController?.clear()
    }

    protected fun <T> liveData(block: suspend LiveDataScope<T>.() -> Unit): Lazy<LiveData<T>> = lazy {
        liveData(context = viewModelScope.coroutineContext, block = block)
    }
}