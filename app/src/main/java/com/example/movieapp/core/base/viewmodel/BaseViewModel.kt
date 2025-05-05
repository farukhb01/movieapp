package com.example.movieapp.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    protected fun launchCoroutine(block: suspend () -> Unit) {
        viewModelScope.launch {
            block()
        }
    }
}