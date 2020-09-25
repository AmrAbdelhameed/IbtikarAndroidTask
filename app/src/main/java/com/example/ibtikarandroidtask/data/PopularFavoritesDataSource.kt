package com.example.ibtikarandroidtask.data

import androidx.lifecycle.LiveData
import com.example.ibtikarandroidtask.domain.dto.db.Popular

interface PopularFavoritesDataSource {
    fun getAllFavorites(): LiveData<List<Popular>>
}