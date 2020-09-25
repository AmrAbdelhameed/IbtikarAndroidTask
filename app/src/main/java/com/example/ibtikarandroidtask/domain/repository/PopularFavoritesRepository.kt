package com.example.ibtikarandroidtask.domain.repository

import androidx.lifecycle.LiveData
import com.example.ibtikarandroidtask.data.PopularFavoritesDataSource
import com.example.ibtikarandroidtask.domain.dto.db.Popular
import com.example.ibtikarandroidtask.data.local.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularFavoritesRepository @Inject constructor(
    private val mAppDatabase: AppDatabase
) : PopularFavoritesDataSource {
    override fun getAllFavorites(): LiveData<List<Popular>> = mAppDatabase.popularDao().getAllFavorites()
}