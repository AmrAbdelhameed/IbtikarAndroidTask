package com.example.ibtikarandroidtask.presentation.main.popular

class PopularEmptyItemViewModel(private val onRetry: () -> Unit) {
    fun onRetryClick() = onRetry.invoke()
}