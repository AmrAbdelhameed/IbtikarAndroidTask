package com.example.ibtikarandroidtask.presentation.main.favorites

class FavoritesItemView(private val action: () -> Unit) {
    fun onItemClick() {
        action.invoke()
    }
}