package com.example.ibtikarandroidtask.data.remote.network

import com.example.ibtikarandroidtask.data.model.api.ImagesResponse
import com.example.ibtikarandroidtask.data.model.api.PopularResponse
import com.example.ibtikarandroidtask.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiEndPoint.ENDPOINT_POPULAR)
    suspend fun getPopular(
        @Query(AppConstants.API_KEY) apiKey: String,
        @Query(AppConstants.PAGE) page: Int
    ): PopularResponse

    @GET(ApiEndPoint.ENDPOINT_IMAGES)
    suspend fun getImages(
        @Path(AppConstants.PERSON_ID) personId: Int,
        @Query(AppConstants.API_KEY) apiKey: String
    ): ImagesResponse
}