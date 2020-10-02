package com.example.popularpeople.domain.repository

import androidx.lifecycle.LiveData
import com.example.popularpeople.data.PopularFavoritesDataSource
import com.example.popularpeople.domain.dto.db.Popular
import com.example.popularpeople.data.local.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularFavoritesRepository @Inject constructor(
    private val mAppDatabase: AppDatabase
) : PopularFavoritesDataSource {
    override fun getAllFavorites(): LiveData<List<Popular>> = mAppDatabase.popularDao().getAllFavorites()
}