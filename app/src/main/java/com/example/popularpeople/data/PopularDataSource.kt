package com.example.popularpeople.data

import com.example.popularpeople.domain.dto.Result
import com.example.popularpeople.domain.dto.api.PopularResponse

interface PopularDataSource {
    suspend fun getPopular(page: Int): Result<PopularResponse>
}