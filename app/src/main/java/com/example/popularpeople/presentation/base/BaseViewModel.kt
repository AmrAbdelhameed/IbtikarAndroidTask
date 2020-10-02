package com.example.popularpeople.presentation.base

import androidx.lifecycle.ViewModel
import com.example.popularpeople.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
}