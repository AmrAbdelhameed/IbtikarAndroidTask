package com.example.ibtikarandroidtask.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ibtikarandroidtask.data.model.db.Popular

@Dao
interface PopularDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(popular: Popular)

    @Delete
    suspend fun delete(popular: Popular)

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getFavoriteById(id: Int): Popular

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): LiveData<List<Popular>>
}