package com.example.ibtikarandroidtask.presentation.base

interface BaseItemListener<T> {
    fun onItemClick(item: T)
    fun onRetryClick()
}