package com.example.popularpeople.presentation.main.popular_details

import com.example.popularpeople.utils.AppConstants
import java.io.Serializable

class ImageDataItem(
    filePath: String?
) : Serializable {
    val imageUrl = AppConstants.MEDIA_BASE_URL + filePath
}