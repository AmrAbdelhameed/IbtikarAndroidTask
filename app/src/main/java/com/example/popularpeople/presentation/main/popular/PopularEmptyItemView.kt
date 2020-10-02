package com.example.popularpeople.presentation.main.popular

class PopularEmptyItemView(private val onRetry: () -> Unit) {
    fun onRetryClick() = onRetry.invoke()
}