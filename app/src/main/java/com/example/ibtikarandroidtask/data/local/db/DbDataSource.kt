package com.example.ibtikarandroidtask.data.local.db

import androidx.lifecycle.LiveData
import com.example.ibtikarandroidtask.data.model.Result
import com.example.ibtikarandroidtask.data.model.db.Popular

interface DbDataSource {
    suspend fun insert(popular: Popular)
    suspend fun delete(popular: Popular)
    suspend fun getFavoriteById(id: Int): Result<Popular>
    fun getAllFavorites(): LiveData<List<Popular>>
}