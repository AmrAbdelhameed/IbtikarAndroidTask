package com.example.ibtikarandroidtask.domain.dto.api

import com.example.ibtikarandroidtask.utils.AppConstants
import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @field:SerializedName("results") val results: List<Result> = emptyList()
)

data class Result(
    val id: Int,
    val name: String,
    @field:SerializedName("known_for_department") val knownForDepartment: String,
    @field:SerializedName("profile_path") val profilePath: String
) {
    val imageUrl
        get() = AppConstants.MEDIA_BASE_URL + profilePath
}