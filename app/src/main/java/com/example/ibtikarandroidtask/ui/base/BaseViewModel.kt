package com.example.ibtikarandroidtask.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.ibtikarandroidtask.data.AppDataSource
import com.example.ibtikarandroidtask.utils.SingleLiveEvent

abstract class BaseViewModel(
    val application: Application,
    val appDataSource: AppDataSource
) : ViewModel() {
    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
}