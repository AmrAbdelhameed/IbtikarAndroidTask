package com.example.popularpeople.data

import androidx.lifecycle.LiveData
import com.example.popularpeople.domain.dto.db.Popular

interface PopularFavoritesDataSource {
    fun getAllFavorites(): LiveData<List<Popular>>
}