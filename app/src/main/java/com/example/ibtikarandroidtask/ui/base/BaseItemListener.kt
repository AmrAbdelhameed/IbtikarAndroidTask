package com.example.ibtikarandroidtask.ui.base

interface BaseItemListener<T> {
    fun onItemClick(item: T)
    fun onRetryClick()
}