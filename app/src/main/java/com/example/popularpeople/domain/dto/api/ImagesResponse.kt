package com.example.popularpeople.domain.dto.api

import com.google.gson.annotations.SerializedName

data class ImagesResponse(
    @field:SerializedName("profiles") val profiles: List<Profile> = emptyList()
)

data class Profile(
    @field:SerializedName("file_path") val filePath: String
)