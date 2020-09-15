package com.example.ibtikarandroidtask

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.ibtikarandroidtask.data.AppDataSource
import com.example.ibtikarandroidtask.ui.main.MainViewModel
import com.example.ibtikarandroidtask.ui.main.favorites.FavoritesViewModel
import com.example.ibtikarandroidtask.ui.main.popular.PopularViewModel
import com.example.ibtikarandroidtask.ui.main.popular_details.PopularDetailsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val application: Application,
    private val appDataSource: AppDataSource
) : NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(application, appDataSource) as T
            }
            modelClass.isAssignableFrom(PopularViewModel::class.java) -> {
                PopularViewModel(application, appDataSource) as T
            }
            modelClass.isAssignableFrom(PopularDetailsViewModel::class.java) -> {
                PopularDetailsViewModel(application, appDataSource) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(application, appDataSource) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}