package com.example.popularpeople.data

import androidx.lifecycle.LiveData
import com.example.popularpeople.domain.dto.Result
import com.example.popularpeople.domain.dto.api.ImagesResponse
import com.example.popularpeople.domain.dto.db.Popular

interface PopularDetailsDataSource {
    suspend fun insert(popular: Popular)
    suspend fun delete(popular: Popular)
    suspend fun getFavoriteById(id: Int): Result<Popular>
    suspend fun getImages(personId: Int): Result<ImagesResponse>
}