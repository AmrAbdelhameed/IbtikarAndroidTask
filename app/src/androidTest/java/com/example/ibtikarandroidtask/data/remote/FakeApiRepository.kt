package com.example.ibtikarandroidtask.data.remote

import com.example.ibtikarandroidtask.data.model.Result
import com.example.ibtikarandroidtask.data.model.api.ImagesResponse
import com.example.ibtikarandroidtask.data.model.api.PopularResponse
import com.example.ibtikarandroidtask.data.remote.ApiDataSource

class FakeApiRepository(
    private val popularResponseTestData: PopularResponse,
    private val imagesResponseTestData: ImagesResponse
) : ApiDataSource {
    override suspend fun getPopular(page: Int): Result<PopularResponse> {
        return Result.Success(popularResponseTestData)
    }

    override suspend fun getImages(personId: Int): Result<ImagesResponse> {
        return Result.Success(imagesResponseTestData)
    }

}