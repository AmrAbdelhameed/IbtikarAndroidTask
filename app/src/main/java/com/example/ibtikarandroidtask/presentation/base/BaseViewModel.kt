package com.example.ibtikarandroidtask.presentation.base

import androidx.lifecycle.ViewModel
import com.example.ibtikarandroidtask.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
}