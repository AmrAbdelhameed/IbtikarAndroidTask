package com.example.ibtikarandroidtask.presentation.main.popular

class PopularItemView(private val onItemClick: () -> Unit) {
    fun onItemClick() = onItemClick.invoke()
}