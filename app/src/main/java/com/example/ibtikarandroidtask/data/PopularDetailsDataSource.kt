package com.example.ibtikarandroidtask.data

import androidx.lifecycle.LiveData
import com.example.ibtikarandroidtask.domain.dto.Result
import com.example.ibtikarandroidtask.domain.dto.api.ImagesResponse
import com.example.ibtikarandroidtask.domain.dto.db.Popular

interface PopularDetailsDataSource {
    suspend fun insert(popular: Popular)
    suspend fun delete(popular: Popular)
    suspend fun getFavoriteById(id: Int): Result<Popular>
    suspend fun getImages(personId: Int): Result<ImagesResponse>
}