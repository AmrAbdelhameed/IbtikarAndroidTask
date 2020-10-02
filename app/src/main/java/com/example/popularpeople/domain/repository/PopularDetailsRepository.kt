package com.example.popularpeople.domain.repository

import com.example.popularpeople.data.PopularDetailsDataSource
import com.example.popularpeople.data.local.AppDatabase
import com.example.popularpeople.data.remote.ApiService
import com.example.popularpeople.di.ApiInfo
import com.example.popularpeople.domain.dto.Result
import com.example.popularpeople.domain.dto.api.ImagesResponse
import com.example.popularpeople.domain.dto.db.Popular
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