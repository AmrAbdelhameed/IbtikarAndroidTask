package com.example.ibtikarandroidtask.presentation.main.popular

class PopularEmptyItemView(private val onRetry: () -> Unit) {
    fun onRetryClick() = onRetry.invoke()
}