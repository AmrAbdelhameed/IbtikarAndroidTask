package com.example.popularpeople.presentation.base

interface BaseItemListener<T> {
    fun onItemClick(item: T)
    fun onRetryClick()
}