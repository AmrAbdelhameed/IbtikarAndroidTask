package com.example.ibtikarandroidtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.ibtikarandroidtask.data.PopularDataSource
import com.example.ibtikarandroidtask.data.PopularDetailsDataSource
import com.example.ibtikarandroidtask.data.PopularFavoritesDataSource
import com.example.ibtikarandroidtask.presentation.main.MainViewModel
import com.example.ibtikarandroidtask.presentation.main.favorites.FavoritesViewModel
import com.example.ibtikarandroidtask.presentation.main.popular.PopularViewModel
import com.example.ibtikarandroidtask.presentation.main.popular_details.PopularDetailsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val popularDataSource: PopularDataSource,
    private val popularDetailsDataSource: PopularDetailsDataSource,
    private val popularFavoritesDataSource: PopularFavoritesDataSource
) : NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel() as T
            }
            modelClass.isAssignableFrom(PopularViewModel::class.java) -> {
                PopularViewModel(popularDataSource) as T
            }
            modelClass.isAssignableFrom(PopularDetailsViewModel::class.java) -> {
                PopularDetailsViewModel(popularDetailsDataSource) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(popularFavoritesDataSource) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}