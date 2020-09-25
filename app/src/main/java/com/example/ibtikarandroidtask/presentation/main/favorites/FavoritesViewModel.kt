package com.example.ibtikarandroidtask.presentation.main.favorites

import androidx.lifecycle.LiveData
import com.example.ibtikarandroidtask.data.PopularFavoritesDataSource
import com.example.ibtikarandroidtask.domain.dto.db.Popular
import com.example.ibtikarandroidtask.presentation.base.BaseViewModel

class FavoritesViewModel(
    favoritesDataSource: PopularFavoritesDataSource
) : BaseViewModel() {
    val popularLiveData: LiveData<List<Popular>> = favoritesDataSource.getAllFavorites()
}