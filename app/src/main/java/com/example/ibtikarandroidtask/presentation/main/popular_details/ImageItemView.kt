package com.example.ibtikarandroidtask.presentation.main.popular_details

class ImageItemView(private val onItemClick: () -> Unit) {
    fun onItemClick() = onItemClick.invoke()
}