package com.example.ibtikarandroidtask.domain.repository

import com.example.ibtikarandroidtask.domain.dto.Result
import com.example.ibtikarandroidtask.domain.dto.api.PopularResponse
import com.example.ibtikarandroidtask.data.PopularDataSource
import com.example.ibtikarandroidtask.data.remote.ApiService
import com.example.ibtikarandroidtask.di.ApiInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularRepository @Inject constructor(
    private val apiService: ApiService,
    @param:ApiInfo private val apiKey: String
) : PopularDataSource {
    override suspend fun getPopular(page: Int): Result<PopularResponse> {
        return try {
            Result.Success(apiService.getPopular(apiKey, page))
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }
}