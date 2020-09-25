package com.example.ibtikarandroidtask.data

import com.example.ibtikarandroidtask.domain.dto.Result
import com.example.ibtikarandroidtask.domain.dto.api.PopularResponse

interface PopularDataSource {
    suspend fun getPopular(page: Int): Result<PopularResponse>
}