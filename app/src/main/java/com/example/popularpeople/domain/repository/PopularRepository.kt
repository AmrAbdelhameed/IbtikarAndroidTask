package com.example.popularpeople.domain.repository

import com.example.popularpeople.domain.dto.Result
import com.example.popularpeople.domain.dto.api.PopularResponse
import com.example.popularpeople.data.PopularDataSource
import com.example.popularpeople.data.remote.ApiService
import com.example.popularpeople.di.ApiInfo
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