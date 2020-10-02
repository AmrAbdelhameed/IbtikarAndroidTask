package com.example.popularpeople.presentation.main.favorites

import androidx.lifecycle.LiveData
import com.example.popularpeople.data.PopularFavoritesDataSource
import com.example.popularpeople.domain.dto.db.Popular
import com.example.popularpeople.presentation.base.BaseViewModel

class FavoritesViewModel(
    favoritesDataSource: PopularFavoritesDataSource
) : BaseViewModel() {
    val popularLiveData: LiveData<List<Popular>> = favoritesDataSource.getAllFavorites()
}