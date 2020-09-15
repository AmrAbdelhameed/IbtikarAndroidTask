package com.example.ibtikarandroidtask.data.remote

import com.example.ibtikarandroidtask.data.model.Result
import com.example.ibtikarandroidtask.data.model.api.ImagesResponse
import com.example.ibtikarandroidtask.data.model.api.PopularResponse

interface ApiDataSource {
    suspend fun getPopular(page: Int): Result<PopularResponse>
    suspend fun getImages(personId: Int): Result<ImagesResponse>
}