package com.example.ibtikarandroidtask.domain.repository

import com.example.ibtikarandroidtask.data.PopularDetailsDataSource
import com.example.ibtikarandroidtask.data.local.AppDatabase
import com.example.ibtikarandroidtask.data.remote.ApiService
import com.example.ibtikarandroidtask.di.ApiInfo
import com.example.ibtikarandroidtask.domain.dto.Result
import com.example.ibtikarandroidtask.domain.dto.api.ImagesResponse
import com.example.ibtikarandroidtask.domain.dto.db.Popular
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularDetailsRepository @Inject constructor(
    private val mAppDatabase: AppDatabase,
    private val apiService: ApiService,
    @param:ApiInfo private val apiKey: String
) : PopularDetailsDataSource {
    override suspend fun insert(popular: Popular) = mAppDatabase.popularDao().insert(popular)
    override suspend fun delete(popular: Popular) = mAppDatabase.popularDao().delete(popular)
    override suspend fun getFavoriteById(id: Int): Result<Popular> {
        return try {
            Result.Success(mAppDatabase.popularDao().getFavoriteById(id))
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun getImages(personId: Int): Result<ImagesResponse> {
        return try {
            Result.Success(apiService.getImages(personId, apiKey))
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }
}