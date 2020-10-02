package com.example.popularpeople.presentation.main.favorites

class FavoritesItemView(private val action: () -> Unit) {
    fun onItemClick() {
        action.invoke()
    }
}