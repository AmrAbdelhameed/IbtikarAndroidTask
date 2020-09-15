package com.example.ibtikarandroidtask.ui.main.popular

class PopularEmptyItemViewModel(private val onRetry: () -> Unit) {
    fun onRetryClick() = onRetry.invoke()
}