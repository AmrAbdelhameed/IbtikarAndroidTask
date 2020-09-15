package com.example.ibtikarandroidtask.data.remote

import com.example.ibtikarandroidtask.data.model.Result
import com.example.ibtikarandroidtask.data.model.api.ImagesResponse
import com.example.ibtikarandroidtask.data.model.api.PopularResponse
import com.example.ibtikarandroidtask.data.remote.network.ApiService
import com.example.ibtikarandroidtask.di.ApiInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiService: ApiService,
    @param:ApiInfo private val apiKey: String
) : ApiDataSource {
    override suspend fun getPopular(page: Int): Result<PopularResponse> {
        return try {
            Result.Success(apiService.getPopular(apiKey, page))
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